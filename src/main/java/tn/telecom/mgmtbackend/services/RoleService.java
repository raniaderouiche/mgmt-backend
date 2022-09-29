package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();
    Role saveRole(Role role);
    void deleteRole(Long id) throws NotFoundException;
}
