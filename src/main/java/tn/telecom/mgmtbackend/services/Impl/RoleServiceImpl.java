package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Role;
import tn.telecom.mgmtbackend.repositories.RoleRepository;
import tn.telecom.mgmtbackend.services.RoleService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {
        return this.roleRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) throws NotFoundException {
        this.roleRepository.deleteById(id);
    }
}
