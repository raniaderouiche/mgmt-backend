package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.CustomFile;
import tn.telecom.mgmtbackend.model.Organization;
import tn.telecom.mgmtbackend.model.User;
import tn.telecom.mgmtbackend.repositories.CustomFileRepository;
import tn.telecom.mgmtbackend.repositories.OrganizationRepository;
import tn.telecom.mgmtbackend.repositories.UserRepository;
import tn.telecom.mgmtbackend.services.OrganizationService;

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

    @Override
    public List<Organization> getOrganizations() {
        return organizationRepository.findAll();
    }

    @Override
    public void saveOrganization(String name, String code, String activitySector, String email,
                                 String country, String region, String address, String phone,
                                 String directorFirstName, String directorLastName, String directorPhone,
                                 String directorEmail,String admin, MultipartFile document, MultipartFile image) throws Exception{

        Organization organization = new Organization(name,code,activitySector,email,country,region,address,phone, directorFirstName,  directorLastName,  directorPhone,
                 directorEmail);

        User user = userRepository.findByUsername(admin);
        organization.setAdmin(user);

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
            organization.setImage(documentFile);
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
    public List<Organization> getOrganizationsInWaiting() {
        return organizationRepository.findByStatusIsNull();
    }

    @Override
    public void updateOrganization(Organization organization) throws NotFoundException {
        Organization existing_org;
        if(organizationRepository.existsById(organization.getId())){
            existing_org = organizationRepository.getById(organization.getId());
            existing_org.setName(organization.getName());
            existing_org.setCode(organization.getCode());
            existing_org.setActivitySector(organization.getActivitySector());
            existing_org.setEmail(organization.getEmail());
            existing_org.setCountry(organization.getCountry());
            existing_org.setRegion(organization.getRegion());
            existing_org.setAddress(organization.getAddress());
            existing_org.setPhone(organization.getPhone());
            existing_org.setDirectorFirstName(organization.getDirectorFirstName());
            existing_org.setDirectorLastName(organization.getDirectorLastName());
            existing_org.setDirectorPhone(organization.getDirectorPhone());
            existing_org.setDirectorEmail(organization.getDirectorEmail());
            organizationRepository.save(existing_org);
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public void activateOrganization(Long id) throws NotFoundException {
        if (this.organizationRepository.existsById(id)) {
            Organization existing_org = organizationRepository.getById(id);
            User user = userRepository.findByUsername(existing_org.getAdmin().getUsername());
            existing_org.setStatus(true);
            user.setIsActive(true);
            organizationRepository.save(existing_org);
            userRepository.save(user);
        } else {
            throw new NotFoundException();
        }
    }
}
