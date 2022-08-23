package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.BusinessSector;
import tn.telecom.mgmtbackend.repositories.BusinessSectorRepository;
import tn.telecom.mgmtbackend.services.BusinessSectorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessSectorServiceImpl implements BusinessSectorService {

    @Autowired
    private BusinessSectorRepository businessSectorRepository;

    @Override
    public List<BusinessSector> getBusinessSectors() {
        return businessSectorRepository.findAll();
    }

    @Override
    public void saveBusinessSector(BusinessSector sector) {
        businessSectorRepository.save(sector);
    }

    @Override
    public void deleteBusinessSector(Long id) throws NotFoundException {
        if(businessSectorRepository.existsById(id)){
            businessSectorRepository.deleteById(id);
        }else {
            throw new NotFoundException();
        }
    }
}
