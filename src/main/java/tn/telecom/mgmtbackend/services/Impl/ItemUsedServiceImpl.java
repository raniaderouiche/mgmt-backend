package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.ItemUsed;
import tn.telecom.mgmtbackend.repositories.ItemUsedRepository;
import tn.telecom.mgmtbackend.services.ItemUsedService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ItemUsedServiceImpl implements ItemUsedService {

    @Autowired
    private ItemUsedRepository itemUsedRepository;

    @Override
    public List<ItemUsed> getItemsUsedByPurchaseOrder(Long id) {
        return itemUsedRepository.findItemUsedsByPurchaseOrderId(id);
    }

    @Override
    public void saveItemUsed(ItemUsed itemUsed) {
        this.itemUsedRepository.save(itemUsed);
    }

    @Override
    public void deleteItemUsed(Long id) throws NotFoundException {
        if(itemUsedRepository.existsById(id)){
            itemUsedRepository.deleteById(id);
        }else {
            throw new NotFoundException();
        }
    }
}
