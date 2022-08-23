package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Type;

import java.util.List;

public interface TypeService {
    List<Type> getTypes();
    void saveType(Type type);
    void deleteType(Long id) throws NotFoundException;
}
