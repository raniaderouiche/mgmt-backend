package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User> getUsers();
    User getUserByToken(String header);
    User getUserById(Long id);

}
