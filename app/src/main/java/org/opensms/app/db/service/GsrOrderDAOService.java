package org.opensms.app.db.service;

import org.opensms.app.db.controller.GsrOrderDAO;
import org.opensms.app.db.controller.GsrPaymentDAO;
import org.opensms.app.db.controller.IisOrderBatchHasGsrOrderDAO;
import org.opensms.app.db.controller.IisOrderHasBatchDAO;
import org.opensms.app.db.entity.*;
import org.opensms.app.view.entity.GsrOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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

    @Autowired
    private GsrPaymentDAO gsrPaymentDAO;


    @Transactional
    public Long save(GsrOrderModel gsrOrderModel) {
        GsrOrder gsrOrder = new GsrOrder();
        gsrOrder.setCustomer(customerDAOService.getCustomer(Integer.parseInt(gsrOrderModel.getCustomer())));
        gsrOrder.setSellingDateTime(Calendar.getInstance().getTime());
        Long save = gsrOrderDAO.save(gsrOrder);
        gsrOrder.setGsrOrderId(save);


        preOrderDAOService.finishPreorders(gsrOrderModel.getPreOrders());
        IisOrder iisOrder = iisOrderDAOService.getOpenOrder(gsrOrderModel.getSales_person());


        List<IisOrderHasBatch> batchList = iisOrderDAOService.getBatchList(iisOrder.getIisOrderId() + "");
        List<IisOrderBatchHasGsrOrder> gsrOrders = new ArrayList<IisOrderBatchHasGsrOrder>();

        Map<String, Double> itemList = gsrOrderModel.getItemList();
        for (String item_id : itemList.keySet()) {
            IisOrderBatchHasGsrOrderPK pk = new IisOrderBatchHasGsrOrderPK();
            pk.setGsrOrder(gsrOrder.getGsrOrderId());


            IisOrderBatchHasGsrOrder batchHasGsrOrder = new IisOrderBatchHasGsrOrder(pk);
            batchHasGsrOrder.setQuantity(BigDecimal.valueOf(itemList.get(item_id)));


            for (IisOrderHasBatch batch : batchList) {
                batchHasGsrOrder.setIisOrderHasBatch(batch);
                batchHasGsrOrder.setIisOrderBatchHasGsrOrderPK(new IisOrderBatchHasGsrOrderPK(gsrOrder.getGsrOrderId(),
                        batch.getIisOrderHasBatchPK().getIisOrder(), batch.getIisOrderHasBatchPK().getBatch()));

                boolean ok = true;

                for (IisOrderBatchHasGsrOrder order : gsrOrders) {
                    IisOrderBatchHasGsrOrderPK pk1 = order.getId();
                    if (pk1.getIisOrder() == gsrOrder.getGsrOrderId() && pk1.getBatch() == batch.getIisOrderHasBatchPK().getBatch() && batch.getIisOrderHasBatchPK().getIisOrder() == pk.getIisOrder()) {
                        ok = false;
                        break;
                    }
                }

                IisOrderBatchHasGsrOrder order = iisOrderBatchHasGsrOrderDAO.get(batchHasGsrOrder.getIisOrderBatchHasGsrOrderPK());
                if (ok && order == null) {
                    iisOrderBatchHasGsrOrderDAO.save(batchHasGsrOrder);
                    gsrOrders.add(batchHasGsrOrder);
                }
            }


        }
        return gsrOrder.getGsrOrderId();
    }

    @Transactional
    public GsrOrder getOrder(long orderId) {
        return gsrOrderDAO.get(orderId);
    }

    @Transactional
    public List<GsrPayment> getGsrPayments(String gsrorderid) {
        return gsrPaymentDAO.getAllById(gsrorderid);
    }

    @Transactional
    public void saveGsrOrderPayment(GsrPayment gsrPayment) {
        gsrPaymentDAO.save(gsrPayment);
    }

    @Transactional
    public List<GsrOrder> getAllGsrOrdersOfCurrentCustomer(Customer customer) {
        return gsrOrderDAO.getAllGsrOrdersOfCurrentCustomer(customer);
    }
}
