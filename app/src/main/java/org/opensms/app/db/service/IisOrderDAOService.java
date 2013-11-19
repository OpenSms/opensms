package org.opensms.app.db.service;

import org.opensms.app.db.controller.BatchDAO;
import org.opensms.app.db.controller.IisOrderDAO;
import org.opensms.app.db.controller.IisOrderHasBatchDAO;
import org.opensms.app.db.controller.impl.PreOrderDAOController;
import org.opensms.app.db.entity.*;
import org.opensms.app.view.model.IisOrderModel;
import org.opensms.app.view.model.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/16/13
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class IisOrderDAOService {
    @Autowired
    private IisOrderDAO iisOrderDAO;

    @Autowired
    private IisOrderHasBatchDAO iisOrderHasBatchDAO;

    @Autowired
    private BatchDAO batchDAO;

    @Autowired
    private PreOrderDAOController preOrderDAOController;

    public void saveIisOrder(IisOrderModel iisOrderModel) {
        IisOrder iisOrder = iisOrderModel.getIisOrder();

        iisOrder.setIssOrderDateTime(Calendar.getInstance().getTime());
        iisOrderDAO.save(iisOrderModel.getIisOrder());

        // update pre orders. set iis order and close it.
        List<PreOrder> preOrderList = iisOrderModel.getPreOrderList();
        for (PreOrder p : preOrderList) {
            p.setIisOrder(iisOrder);
            p.setIsOpen(false);
            preOrderDAOController.update(p);
        }
    }
}
