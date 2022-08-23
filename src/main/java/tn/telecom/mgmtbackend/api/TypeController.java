package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Type;
import tn.telecom.mgmtbackend.services.TypeService;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/")
    public List<Type> getTypes(){
        return typeService.getTypes();
    }

    @PostMapping("/")
    public void saveType(@RequestBody Type type){
        typeService.saveType(type);
    }

    @DeleteMapping("/{id}")
    public void deleteType(@PathVariable(name = "id") Long id) throws NotFoundException {
        typeService.deleteType(id);
    }
}
