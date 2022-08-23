package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.BusinessSector;

import java.util.List;

public interface BusinessSectorService {
    List<BusinessSector> getBusinessSectors();
    void saveBusinessSector(BusinessSector sector);
    void deleteBusinessSector(Long id) throws NotFoundException;
}
