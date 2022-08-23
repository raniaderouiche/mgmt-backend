package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.BusinessSector;
import tn.telecom.mgmtbackend.services.BusinessSectorService;

import java.util.List;

@RestController
@RequestMapping("/sector")
public class BusinessSectorController {

    @Autowired
    private BusinessSectorService businessSectorService;

    @GetMapping("/")
    public List<BusinessSector> getSectors(){
        return businessSectorService.getBusinessSectors();
    }

    @PostMapping("/")
    public void saveSector(@RequestBody BusinessSector sector){
        businessSectorService.saveBusinessSector(sector);
    }

    @DeleteMapping("/{id}")
    public void deleteSector(@PathVariable(name = "id") Long id) throws NotFoundException {
        businessSectorService.deleteBusinessSector(id);
    }
}
