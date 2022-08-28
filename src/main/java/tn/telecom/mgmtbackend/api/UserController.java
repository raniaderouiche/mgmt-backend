package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.telecom.mgmtbackend.model.Role;
import tn.telecom.mgmtbackend.model.User;
import tn.telecom.mgmtbackend.services.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/roles/addtouser")
    public ResponseEntity<?> saveRole(@RequestParam(name = "username") String username, @RequestParam(name = "roleName") String roleName){
        userService.addRoleToUser(username,roleName);
        return ResponseEntity.ok().build();
    }
}
