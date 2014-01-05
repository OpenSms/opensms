package org.opensms.app.db.service;

import org.opensms.app.db.controller.impl.PreOrderDAOController;
import org.opensms.app.db.controller.impl.PreOrderHasItemDAOController;
import org.opensms.app.db.entity.Customer;
import org.opensms.app.db.entity.PreOrder;
import org.opensms.app.db.entity.PreOrderHasItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/6/13
 * Time: 9:24 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PreOrderDAOService {

    @Autowired
    PreOrderDAOController preOrderDAOController;

    @Autowired
    PreOrderHasItemDAOController preOrderHasItemDAOController;

    /**
     * Save Pre-Order
     * @param preOrder
     * @param preOrderHasItemList
     */

    @Transactional
    public void savePreOrder(PreOrder preOrder, List<PreOrderHasItem> preOrderHasItemList) {

        // Setting pre order default data
        preOrder.setPreOrderDate(Calendar.getInstance().getTime());
        preOrder.setIsOpen(true);




        // need pre order id to save pre order items. so retrieve it when saving pre order
        Long preOrderId = preOrderDAOController.save(preOrder);

        // ** what happens if pre order could not be saved. how can i check whether preOrderId is valid?
        preOrderHasItemDAOController.save(preOrderId, preOrderHasItemList);
    }


    @Transactional
    public List<PreOrder> getPreOrdersAt(String location) {
        return preOrderDAOController.getPreOrdersAt(location);
    }


    @Transactional
    public List<PreOrderHasItem> getPreOrderHasItemsOf(Long preOrderId) {
        return preOrderHasItemDAOController.getPreOrderHasItemsOf(preOrderId);
    }


    @Transactional
    public List<PreOrder> getAll() {
        return preOrderDAOController.getAll();
    }


    @Transactional
    public List<PreOrder> getAllOpenPreOrders() {
        return preOrderDAOController.getAllOpenPreOrders();
    }

    /**
     *
     * @param customerid
     * @return
     */
    @Transactional
    public List<PreOrder> getPreOrdersFrom(String customerid) {
        return preOrderDAOController.getOrdersFrom(Integer.parseInt(customerid));
    }

    /**
     *
     * Finish selected Preorders
     *
     * @param preOrders
     */
    @Transactional
    public void finishPreorders(List<String> preOrders) {
        for (String pre_order_id:preOrders){
            long preOrderId = Long.parseLong(pre_order_id);
            PreOrder preOrder = preOrderDAOController.get(preOrderId);
            preOrder.setIsOpen(false);
            preOrderDAOController.update(preOrder);
        }
    }

    @Transactional
    public List<PreOrder> getAllPreOrdersOfCurrentCustomer(Customer customer) {
        return preOrderDAOController.getOrdersFrom(customer.getId());
    }
}
