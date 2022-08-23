package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Type;
import tn.telecom.mgmtbackend.repositories.TypeRepository;
import tn.telecom.mgmtbackend.services.TypeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<Type> getTypes() {
        return typeRepository.findAll();
    }

    @Override
    public void saveType(Type type) {
        typeRepository.save(type);
    }

    @Override
    public void deleteType(Long id) throws NotFoundException {
        if(typeRepository.existsById(id)){
            typeRepository.deleteById(id);
        }else {
            throw new NotFoundException();
        }
    }
}
