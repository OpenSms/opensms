package org.opensms.app.db.service;

import org.opensms.app.db.controller.impl.PreOrderDAOController;
import org.opensms.app.db.controller.impl.PreOrderHasItemDAOController;
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
@Transactional
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
    public void savePreOrder(PreOrder preOrder, List<PreOrderHasItem> preOrderHasItemList) {

        // Setting pre order default data
        preOrder.setPreOrderDate(Calendar.getInstance().getTime());
        preOrder.setIsOpen(true);

        // need pre order id to save pre order items. so retrieve it when saving pre order
        Long preOrderId = preOrderDAOController.save(preOrder);

        // ** what happens if pre order could not be saved. how can i check whether preOrderId is valid?
        preOrderHasItemDAOController.save(preOrderId, preOrderHasItemList);
    }

    public List<PreOrder> getPreOrdersAt(String location) {
        return preOrderDAOController.getPreOrdersAt(location);
    }

    public List<PreOrderHasItem> getPreOrderHasItemsOf(Long preOrderId) {
        return preOrderHasItemDAOController.getPreOrderHasItemsOf(preOrderId);
    }

    public List<PreOrder> getAll() {
        return preOrderDAOController.getAll();
    }
}
