package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.AccountNotActiveException;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Organization;
import tn.telecom.mgmtbackend.model.Role;
import tn.telecom.mgmtbackend.model.User;
import tn.telecom.mgmtbackend.repositories.OrganizationRepository;
import tn.telecom.mgmtbackend.repositories.RoleRepository;
import tn.telecom.mgmtbackend.repositories.UserRepository;
import tn.telecom.mgmtbackend.services.UserService;
import tn.telecom.mgmtbackend.utils.UserUtils;

import javax.transaction.Transactional;
import java.io.NotActiveException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService,UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }else if (!user.getIsActive()){
            log.error("Account not active");
            throw new AccountNotActiveException();
        }else{
            log.info("User found : {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding new role {} to user {}",username,roleName);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByToken(String header) {
        return UserUtils.getUserFromToken(header);
    }

    @Override
    public User getUserById(Long id) {
        if(this.userRepository.findById(id).isPresent()) {
            return this.userRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public void deleteUserById(Long id) throws NotFoundException {
        if (this.userRepository.findById(id).isPresent()) {
            User user = this.userRepository.findById(id).get();
            user.setOrganization(null);
            this.userRepository.save(user);
            this.userRepository.deleteById(id);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void saveUser(User user, Long orgId) throws NotFoundException {
        if( this.organizationRepository.findById(orgId).isPresent()){
            Organization organization = this.organizationRepository.findById(orgId).get();
            user.setOrganization(organization);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        else {
            throw new NotFoundException();
        }

    }

    @Override
    public List<User> getUsersByOrg(String header) {
        User user = UserUtils.getUserFromToken(header);
        return this.userRepository.findUsersByOrganizationName(user.getOrganization().getName());
    }

}
