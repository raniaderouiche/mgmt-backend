package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Profession;
import tn.telecom.mgmtbackend.repositories.ProfessionRepository;
import tn.telecom.mgmtbackend.services.ProfessionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessionServiceImpl implements ProfessionService {

    @Autowired
    private ProfessionRepository professionRepository;

    @Override
    public List<Profession> getProfessions() {
        return professionRepository.findAll();
    }

    @Override
    public void saveProfession(Profession profession) {
        professionRepository.save(profession);
    }

    @Override
    public void deleteProfession(Long id) throws NotFoundException {
        if(professionRepository.existsById(id)){
            professionRepository.deleteById(id);
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<Profession> getProfessionBySectorId(Long id) {
        return professionRepository.findProfessionBySectorId(id);
    }
}
