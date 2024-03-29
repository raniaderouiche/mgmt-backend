package tn.telecom.mgmtbackend.services;

import org.aspectj.weaver.ast.Or;
import org.springframework.web.multipart.MultipartFile;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Organization;
import tn.telecom.mgmtbackend.model.User;

import java.util.Date;
import java.util.List;

public interface OrganizationService {

    List<Organization> getOrganizations();
    Organization getOrganizationById(Long id);
    Organization getOrganizationByAdminId(Long id);
    void saveOrganization(String name, String code, Long sectorId,
                          String email, String country, String region, String address,
                          String phone, String directorFirstName, String directorLastName,
                          String directorPhone, String directorEmail, String adminUsername,String adminPwd,String adminFN,String adminLN,
                          Date adminDob, String adminGender,String adminPhone,String adminEmail,
                          MultipartFile document, MultipartFile image) throws Exception;
    void deleteOrganization(Long id) throws NotFoundException;
    List<Organization> getActiveOrganizations();
    List<Organization> getOrganizationsInWaiting();
    List<Organization> getRejectedOrganizations();

    void updateOrganization(Long id, String name, String code, Long sectorId,
                            String email, String country, String region, String address,
                            String phone, String directorFirstName, String directorLastName,
                            String directorPhone, String directorEmail, String admin,
                            MultipartFile document, MultipartFile image) throws Exception;

    void activateOrganization(Long id) throws NotFoundException;
    void rejectOrganization(Long id) throws NotFoundException;

    User getOrganizationAdmin(Long id);

    void saveOrganizationObject(Organization organization);

}
