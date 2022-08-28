package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Role;
import tn.telecom.mgmtbackend.model.Type;
import tn.telecom.mgmtbackend.services.RoleService;
import tn.telecom.mgmtbackend.services.TypeService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }

    @PostMapping("/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/roles/save").toUriString());
        return ResponseEntity.created(uri).body(roleService.saveRole(role));
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable(name = "id") Long id) throws NotFoundException {
        roleService.deleteRole(id);
    }


}
