package org.opensms.app.db.service;

import org.opensms.app.db.controller.GsrOrderDAO;
import org.opensms.app.db.controller.IisOrderBatchHasGsrOrderDAO;
import org.opensms.app.db.controller.IisOrderHasBatchDAO;
import org.opensms.app.db.entity.*;
import org.opensms.app.view.entity.GsrOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by dewmal on 1/3/14.
 */
@Service
public class GsrOrderDAOService {
    @Autowired
    private IisOrderHasBatchDAO iisOrderHasBatchDAO;

    @Autowired
    private PreOrderDAOService preOrderDAOService;

    @Autowired
    private GsrOrderDAO gsrOrderDAO;

    @Autowired
    private IisOrderDAOService iisOrderDAOService;

    @Autowired
    private IisOrderBatchHasGsrOrderDAO iisOrderBatchHasGsrOrderDAO;

    @Autowired
    private CustomerDAOService customerDAOService;


    @Transactional
    public Long save(GsrOrderModel gsrOrderModel) {
        GsrOrder gsrOrder = new GsrOrder();
        gsrOrder.setCustomer(customerDAOService.getCustomer(Integer.parseInt(gsrOrderModel.getCustomer())));
        gsrOrder.setSellingDateTime(Calendar.getInstance().getTime());
        Long save = gsrOrderDAO.save(gsrOrder);
        gsrOrder.setGsrOrderId(save);


        preOrderDAOService.finishPreorders(gsrOrderModel.getPreOrders());
        IisOrder iisOrder = iisOrderDAOService.getOpenOrder(gsrOrderModel.getSales_person());


        Map<String, Double> itemList = gsrOrderModel.getItemList();
        for (String item_id : itemList.keySet()) {
            IisOrderBatchHasGsrOrderPK pk = new IisOrderBatchHasGsrOrderPK();
            pk.setGsrOrder(gsrOrder.getGsrOrderId());


            IisOrderBatchHasGsrOrder batchHasGsrOrder = new IisOrderBatchHasGsrOrder(pk);
            batchHasGsrOrder.setQuantity(BigDecimal.valueOf(itemList.get(item_id)));


            IisOrderHasBatchPK iisOrderHasBatchPK=new IisOrderHasBatchPK();
            iisOrderHasBatchPK.setIisOrder(iisOrder.getIisOrderId());
            iisOrderHasBatchPK.setBatch(item_id);
            IisOrderHasBatch iisOrderHasBatch = iisOrderHasBatchDAO.get(iisOrderHasBatchPK);

            batchHasGsrOrder.setIisOrderHasBatch(iisOrderHasBatch);
            iisOrderBatchHasGsrOrderDAO.save(batchHasGsrOrder);

        }
        return gsrOrder.getGsrOrderId();
    }
}
