package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User> getUsers();
    User getUserByToken(String header);
    User getUserById(Long id);

    void deleteUserById(Long id) throws NotFoundException;

    void saveUser(User user, Long orgId) throws NotFoundException;

}
