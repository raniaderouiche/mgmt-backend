package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.ItemRealised;
import tn.telecom.mgmtbackend.repositories.ItemRealisedRepository;
import tn.telecom.mgmtbackend.services.ItemRealisedService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemRealisedServiceImpl implements ItemRealisedService {

    @Autowired
    private ItemRealisedRepository itemRealisedRepository;

    @Override
    public List<ItemRealised> getItemsRealised() {
        return this.itemRealisedRepository.findAll();
    }

    @Override
    public ItemRealised getItemRealisedById(Long id) throws NotFoundException {
        if(this.itemRealisedRepository.findById(id).isPresent()) {
            return this.itemRealisedRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public void saveItemRealised(ItemRealised itemRealised) {
        this.itemRealisedRepository.save(itemRealised);
    }

    @Override
    public void deleteItemRealised(Long id) throws NotFoundException {
        if(itemRealisedRepository.existsById(id)){
            itemRealisedRepository.deleteById(id);
        }else {
            throw new NotFoundException();
        }
    }
}
