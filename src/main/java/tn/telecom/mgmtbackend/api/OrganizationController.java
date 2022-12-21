package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Organization;
import tn.telecom.mgmtbackend.model.User;
import tn.telecom.mgmtbackend.services.OrganizationService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/all")
    public List<Organization> getOrganizations(){
        return organizationService.getOrganizations();
    }

    @GetMapping("/active")
    public List<Organization> getActiveOrganizations(){
        return organizationService.getActiveOrganizations();
    }

    @GetMapping("/rejected")
    public List<Organization> getRejectedOrganizations(){
        return organizationService.getRejectedOrganizations();
    }

    @GetMapping("/waitlist")
    public List<Organization> getOrganizationsInWaiting(){
        return organizationService.getOrganizationsInWaiting();
    }

    @GetMapping("/{id}")
    public Organization getOrganizationById(@PathVariable(name = "id") Long id) {
        return organizationService.getOrganizationById(id);
    }

    @GetMapping("/admin/{id}")
    public Organization getOrganizationByAdminId(@PathVariable(name = "id") Long id) {
        return organizationService.getOrganizationByAdminId(id);
    }


    @PostMapping("/")
    public void saveOrganization(@Nullable @RequestParam(name = "document") MultipartFile document,
                                 @Nullable @RequestParam(name = "image") MultipartFile image,
                                 @Nullable @RequestParam("sectorId") Long sectorId,
                                 @Nullable @RequestParam("name") String name,
                                 @Nullable @RequestParam("code") String code,
                                 @Nullable @RequestParam("email") String email,
                                 @Nullable @RequestParam("country") String country,
                                 @Nullable @RequestParam("region")String region,
                                 @Nullable @RequestParam("address")String address,
                                 @Nullable @RequestParam("phone")String phone,
                                 @Nullable @RequestParam("directorFirstName") String directorFirstName,
                                 @Nullable @RequestParam("directorLastName") String directorLastName,
                                 @Nullable @RequestParam("directorPhone")String directorPhone,
                                 @Nullable @RequestParam("directorEmail") String directorEmail,
                                 @Nullable @RequestParam("adminUsername") String adminUsername,
                                 @Nullable @RequestParam("adminPwd") String adminPwd,
                                 @Nullable @RequestParam("adminFN") String adminFN,
                                 @Nullable @RequestParam("adminLN") String adminLN,
                                 @Nullable @RequestParam("adminGender") String adminGender,
                                 @Nullable @RequestParam("adminDob") Date adminDob,
                                 @Nullable @RequestParam("adminPhone") String adminPhone,
                                 @Nullable @RequestParam("adminEmail") String adminEmail
    )throws Exception{
        System.out.println(name+ code+ sectorId+ email+ country+ region+ address+ phone+directorFirstName+ directorLastName+ directorPhone+directorEmail+document+image);
        organizationService.saveOrganization(name, code, sectorId, email, country, region, address, phone, directorFirstName, directorLastName,directorPhone, directorEmail,adminUsername,adminPwd,adminFN,adminLN,adminDob, adminGender,adminPhone,adminEmail, document, image);

    }

    @PutMapping("/")
    public void updateOrganization(@Nullable @RequestParam(name = "document") MultipartFile document,
                                 @Nullable @RequestParam(name = "image") MultipartFile image,
                                 @Nullable @RequestParam("id") Long id,
                                 @Nullable @RequestParam("sectorId") Long sectorId,
                                 @Nullable @RequestParam("name") String name,
                                 @Nullable @RequestParam("code") String code,
                                 @Nullable @RequestParam("email") String email,
                                 @Nullable @RequestParam("country") String country,
                                 @Nullable @RequestParam("region")String region,
                                 @Nullable @RequestParam("address")String address,
                                 @Nullable @RequestParam("phone")String phone,
                                 @Nullable @RequestParam("admin")String admin,
                                 @Nullable @RequestParam("directorFirstName") String directorFirstName,
                                 @Nullable @RequestParam("directorLastName") String directorLastName,
                                 @Nullable @RequestParam("directorPhone")String directorPhone,
                                 @Nullable @RequestParam("directorEmail") String directorEmail)throws Exception{
        organizationService.updateOrganization(id, name, code, sectorId, email, country, region, address, phone, directorFirstName, directorLastName, directorPhone, directorEmail,admin, document, image);

    }

    @DeleteMapping("/{id}")
    public void deleteOrganization(@PathVariable(name = "id") Long id) throws NotFoundException {
        organizationService.deleteOrganization(id);
    }

    @PutMapping("/activate/{id}")
    public void activateOrganization(@PathVariable(name = "id") Long id) throws NotFoundException {
        organizationService.activateOrganization(id);
    }

    @PutMapping("/reject/{id}")
    public void rejectOrganization(@PathVariable(name = "id") Long id) throws NotFoundException {
        organizationService.rejectOrganization(id);
    }

    @GetMapping("/get-admin/{id}")
    public User getOrganizationAdmin(@PathVariable(name = "id") Long id){
        return organizationService.getOrganizationAdmin(id);
    }

}
