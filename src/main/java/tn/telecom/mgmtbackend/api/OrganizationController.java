package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Organization;
import tn.telecom.mgmtbackend.services.OrganizationService;

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

    @GetMapping("/waitlist")
    public List<Organization> getOrganizationsInWaiting(){
        return organizationService.getOrganizationsInWaiting();
    }

    @PostMapping("/")
    public void saveOrganization(@Nullable @RequestParam(name = "document") MultipartFile document,
                                 @Nullable @RequestParam(name = "image") MultipartFile image,
                                 @Nullable @RequestParam("name") String name,
                                 @Nullable @RequestParam("code") String code,
                                 @Nullable @RequestParam("activitySector") String activitySector,
                                 @Nullable @RequestParam("email") String email,
                                 @Nullable @RequestParam("country") String country,
                                 @Nullable @RequestParam("region")String region,
                                 @Nullable @RequestParam("address")String address,
                                 @Nullable @RequestParam("phone")String phone,
                                 @Nullable @RequestParam("directorFirstName") String directorFirstName,
                                 @Nullable @RequestParam("directorLastName") String directorLastName,
                                 @Nullable @RequestParam("directorPhone")String directorPhone,
                                 @Nullable @RequestParam("directorEmail") String directorEmail)throws Exception{
        organizationService.saveOrganization(name, code, activitySector, email, country, region, address, phone, directorFirstName, directorLastName, directorPhone, directorEmail, document, image);

    }

    @DeleteMapping("/{id}")
    public void deleteOrganization(@PathVariable(name = "id") Long id) throws NotFoundException {
        organizationService.deleteOrganization(id);
    }

    @PutMapping("/activate/{id}")
    public void activateOrganization(@PathVariable(name = "id") Long id) throws NotFoundException {
        organizationService.activateOrganization(id);
    }

}
