package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Profession;

import java.util.List;

public interface ProfessionService {
    List<Profession> getProfessions();
    void saveProfession(Profession profession);
    void deleteProfession(Long id) throws NotFoundException;

    List<Profession> getProfessionBySectorId(Long id);
}
