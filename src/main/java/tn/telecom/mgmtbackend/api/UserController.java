package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.User;
import tn.telecom.mgmtbackend.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }


    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users/create")
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PostMapping("/users/save")
    public void saveUser(@RequestBody User user, @RequestParam("orgId") Long orgId) throws NotFoundException {
        userService.saveUser(user,orgId);
    }

    @GetMapping("/user/profile")
    public User getUserByToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        System.out.println("get user by token = " + authorizationHeader);
        return userService.getUserByToken(authorizationHeader);
    }

    @PostMapping("/roles/addtouser")
    public ResponseEntity<?> saveRole(@RequestParam(name = "username") String username, @RequestParam(name = "roleName") String roleName){
        userService.addRoleToUser(username,roleName);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable(name = "id") Long id) throws NotFoundException {
        userService.deleteUserById(id);
    }
}
