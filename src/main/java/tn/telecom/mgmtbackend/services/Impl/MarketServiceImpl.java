package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Market;
import tn.telecom.mgmtbackend.model.PurchaseOrder;
import tn.telecom.mgmtbackend.model.User;
import tn.telecom.mgmtbackend.model.WorkOrder;
import tn.telecom.mgmtbackend.repositories.MarketRepository;
import tn.telecom.mgmtbackend.repositories.PurchaseOrderRepository;
import tn.telecom.mgmtbackend.repositories.WorkOrderRepository;
import tn.telecom.mgmtbackend.services.MarketService;
import tn.telecom.mgmtbackend.services.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Market> getMarkets() {
        return marketRepository.findAll();
    }

    @Override
    public List<Market> getMarketsByType(String type) {
        return this.marketRepository.findAllByType(type);
    }

    @Override
    public void saveMarket(Market market,String header) {
        User user = userService.getUserByToken(header);
        market.setOrganization(user.getOrganization());
        marketRepository.save(market);
    }

    @Override
    public void deleteMarket(Long id) throws NotFoundException {
        if(marketRepository.existsById(id)){
            marketRepository.deleteById(id);
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public Market getMarketById(Long id) {
        if(this.marketRepository.findById(id).isPresent()) {
            return this.marketRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public Market getMarketByPurchaseOrderID(Long id) {
        PurchaseOrder order = new PurchaseOrder();
        if(purchaseOrderRepository.findById(id).isPresent()){
            order = purchaseOrderRepository.findById(id).get();
        }
        return order.getMarket();
    }

    @Override
    public Market getMarketByWorkOrderID(Long id) {
        WorkOrder workOrder = new WorkOrder();
        if(workOrderRepository.findById(id).isPresent()){
            workOrder = workOrderRepository.findById(id).get();
        }
        return workOrder.getPurchaseOrder().getMarket();
    }

}
