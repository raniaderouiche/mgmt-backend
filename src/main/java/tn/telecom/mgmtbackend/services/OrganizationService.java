package tn.telecom.mgmtbackend.services;

import org.springframework.web.multipart.MultipartFile;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Organization;

import java.util.List;

public interface OrganizationService {

    List<Organization> getOrganizations();
    void saveOrganization(String name, String code, String activitySector,
                          String email, String country, String region, String address,
                          String phone, String directorFirstName, String directorLastName,
                          String directorPhone, String directorEmail, String admin,
                          MultipartFile document, MultipartFile image) throws Exception;
    void deleteOrganization(Long id) throws NotFoundException;
    List<Organization> getActiveOrganizations();
    List<Organization> getOrganizationsInWaiting();
    void updateOrganization(Organization organization) throws NotFoundException;

    void activateOrganization(Long id) throws NotFoundException;
}
