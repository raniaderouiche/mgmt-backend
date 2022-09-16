package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Profession;
import tn.telecom.mgmtbackend.services.ProfessionService;

import java.util.List;

@RestController
@RequestMapping("/profession")
public class ProfessionController {

    @Autowired
    private ProfessionService professionService;

    @GetMapping("/")
    public List<Profession> getProfessions(){
        return professionService.getProfessions();
    }

    @PostMapping("/")
    public void saveProfession(@RequestBody Profession profession){
        System.out.println(profession);
        professionService.saveProfession(profession);
    }

    @DeleteMapping("/{id}")
    public void deleteProfession(@PathVariable(name = "id") Long id) throws NotFoundException {
        professionService.deleteProfession(id);
    }

    @GetMapping("/bySector/{id}")
    public List<Profession> getProfessionBySectorId(@PathVariable(name = "id") Long id){
        return professionService.getProfessionBySectorId(id);
    }
}
