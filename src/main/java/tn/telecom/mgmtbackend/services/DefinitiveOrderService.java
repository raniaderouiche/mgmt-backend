package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.DefinitiveOrder;

import java.util.List;

public interface DefinitiveOrderService {
    List<DefinitiveOrder> getDefinitiveOrders();
    DefinitiveOrder getDefinitiveOrderById(Long id) throws NotFoundException;
    void saveDefinitiveOrder(DefinitiveOrder definitiveOrder);
    void deleteDefinitiveOrder(Long id) throws NotFoundException;
}
