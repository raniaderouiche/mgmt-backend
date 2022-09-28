package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.BusinessSector;
import tn.telecom.mgmtbackend.model.CustomFile;
import tn.telecom.mgmtbackend.model.Organization;
import tn.telecom.mgmtbackend.model.User;
import tn.telecom.mgmtbackend.repositories.BusinessSectorRepository;
import tn.telecom.mgmtbackend.repositories.CustomFileRepository;
import tn.telecom.mgmtbackend.repositories.OrganizationRepository;
import tn.telecom.mgmtbackend.repositories.UserRepository;
import tn.telecom.mgmtbackend.services.OrganizationService;
import tn.telecom.mgmtbackend.services.UserService;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private CustomFileRepository customFileRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BusinessSectorRepository businessSectorRepository;

    @Autowired
    private EmailServiceImpl emailService;
    @Override
    public List<Organization> getOrganizations() {
        return organizationRepository.findAll();
    }

    @Override
    public Organization getOrganizationById(Long id) {
        if(this.organizationRepository.findById(id).isPresent()) {
            return this.organizationRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public Organization getOrganizationByAdminId(Long id) {
        User admin = userService.getUserById(id);
        if(admin == null) return null;

        if(this.organizationRepository.existsByAdminOrg(admin)){
            return this.organizationRepository.findByAdminOrg(admin);
        }else{
            return null;
        }
    }

    @Override
    public void saveOrganization(String name, String code, Long sectorId, String email,
                                 String country, String region, String address, String phone,
                                 String directorFirstName, String directorLastName, String directorPhone,
                                 String directorEmail,Long adminId, MultipartFile document, MultipartFile image) throws Exception{

        BusinessSector sector;
        if(sectorId != null){
            sector = this.businessSectorRepository.getById(sectorId);
        }else{
            sector = null;
        }

        Organization organization = new Organization(name,code,sector,email,country,region,address,phone, directorFirstName, directorLastName,  directorPhone,
                 directorEmail);

        //it doesnt add the user to organization on database!!
        User user = userRepository.getById(adminId);
        System.out.println(user);
        organization.setAdminOrg(user);

        if (image != null) {
            String imageName = StringUtils.cleanPath(image.getOriginalFilename());
            CustomFile imageFile = new CustomFile(imageName, Base64.getEncoder().encodeToString(image.getBytes()));
            this.customFileRepository.save(imageFile);
            organization.setImage(imageFile);
        }
        if (document != null) {
            String documentName = StringUtils.cleanPath(document.getOriginalFilename());
            CustomFile documentFile = new CustomFile(documentName, Base64.getEncoder().encodeToString(document.getBytes()));
            this.customFileRepository.save(documentFile);
            organization.setDocument(documentFile);
        }
        organizationRepository.save(organization);

    }

    @Override
    public void deleteOrganization(Long id) throws NotFoundException {
        if (this.organizationRepository.existsById(id)) {
            this.organizationRepository.deleteById(id);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<Organization> getActiveOrganizations() {
        return organizationRepository.findByStatusIsTrue();
    }

    @Override
    public List<Organization> getRejectedOrganizations() {
        return organizationRepository.findByStatusIsFalse();
    }

    @Override
    public List<Organization> getOrganizationsInWaiting() {
        return organizationRepository.findByStatusIsNull();
    }

    @Override
    public void updateOrganization(Long id, String name, String code, Long sectorId, String email,
                                   String country, String region, String address, String phone,
                                   String directorFirstName, String directorLastName, String directorPhone,
                                   String directorEmail, String admin, MultipartFile document, MultipartFile image) throws Exception {
        BusinessSector sector;
        if(sectorId != null){
            sector = this.businessSectorRepository.getById(sectorId);
        }else{
            sector = null;
        }

        Organization organization = new Organization(id,name,code,sector,email,country,region,address,phone, directorFirstName,  directorLastName,  directorPhone,
                directorEmail);
        Organization existing_org;
        if(organizationRepository.existsById(organization.getId())){

            Long replacedFileId = null;

            existing_org = organizationRepository.getById(organization.getId());
            existing_org.setName(organization.getName());
            existing_org.setCode(organization.getCode());
            existing_org.setSector(organization.getSector());
            existing_org.setEmail(organization.getEmail());
            existing_org.setCountry(organization.getCountry());
            existing_org.setRegion(organization.getRegion());
            existing_org.setAddress(organization.getAddress());
            existing_org.setPhone(organization.getPhone());
            existing_org.setDirectorFirstName(organization.getDirectorFirstName());
            existing_org.setDirectorLastName(organization.getDirectorLastName());
            existing_org.setDirectorPhone(organization.getDirectorPhone());
            existing_org.setDirectorEmail(organization.getDirectorEmail());

            if (document != null) {
                String documentName = StringUtils.cleanPath(document.getOriginalFilename());
                CustomFile documentFile = new CustomFile(documentName, Base64.getEncoder().encodeToString(document.getBytes()));
                this.customFileRepository.save(documentFile);
                if(existing_org.getDocument() != null){
                    replacedFileId = existing_org.getDocument().getId();
                    System.out.println(replacedFileId);
                }
                existing_org.setDocument(documentFile);
            }else {
                if(existing_org.getDocument() != null){
                    replacedFileId = existing_org.getDocument().getId();
                }
                existing_org.setDocument(null);
            }

            organizationRepository.save(existing_org);

            if (replacedFileId != null) {
                this.customFileRepository.deleteById(replacedFileId);
            }

        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public void activateOrganization(Long id) throws NotFoundException {
        if (this.organizationRepository.existsById(id)) {
            Organization existing_org = organizationRepository.getById(id);
            //User user = userRepository.findByUsername(existing_org.getAdmin_org().getUsername());
            existing_org.setStatus(true);
            //user.setIsActive(true);
            organizationRepository.save(existing_org);
            System.out.println("beforeeeeeeeeee email (saved)");
            this.emailService.sendEmail(existing_org.getAdminOrg().getId());
            System.out.println("afteeeeeeeeeeeeeeeeeeeeeeeeer email (saved)");
            //userRepository.save(user);

        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void rejectOrganization(Long id) throws NotFoundException {
        if (this.organizationRepository.existsById(id)) {
            Organization existing_org = organizationRepository.getById(id);
            //User user = userRepository.findByUsername(existing_org.getAdmin_org().getUsername());
            existing_org.setStatus(false);
            //user.setIsActive(true);
            organizationRepository.save(existing_org);
            //userRepository.save(user);
        } else {
            throw new NotFoundException();
        }
    }
}
