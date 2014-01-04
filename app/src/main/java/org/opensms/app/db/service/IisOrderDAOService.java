package org.opensms.app.db.service;

import org.apache.log4j.Logger;
import org.opensms.app.db.controller.BatchDAO;
import org.opensms.app.db.controller.IisOrderDAO;
import org.opensms.app.db.controller.IisOrderHasBatchDAO;
import org.opensms.app.db.controller.impl.PreOrderDAOController;
import org.opensms.app.db.entity.*;
import org.opensms.app.view.model.IisOrderModel;
import org.opensms.app.view.model.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
public class IisOrderDAOService {

    private static final Logger LOGGER = Logger.getLogger(IisOrderDAOService.class);

    @Autowired
    private IisOrderDAO iisOrderDAO;

    @Autowired
    private IisOrderHasBatchDAO iisOrderHasBatchDAO;

    @Autowired
    private BatchDAO batchDAO;

    @Autowired
    private PreOrderDAOController preOrderDAOController;

    /**
     * Saving iis order
     *
     * * assumption: there are enough items to satisfy requirements.
     *
     * @param iisOrderModel
     */


    @Transactional
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


        List<ItemModel> itemModels = iisOrderModel.getItemModelList();
        // **** assumption: there are enough items to satisfy requirements.
        // 1. get all available batches for given item
        // 2. check each batch's remaining quantity and get items from those. (insert to IisOrderHasBatch)
        for (ItemModel itemModel : itemModels) {
            List<Batch> batches = batchDAO.getBatchesWithItemId(itemModel.getItem());

            BigDecimal itemRemainingQty = itemModel.getQuantity();
            for (Batch batch : batches) {

                if (batch.getRemainingQuantity().compareTo(itemRemainingQty) == 1) {

                    batch.setRemainingQuantity(batch.getRemainingQuantity().subtract(itemRemainingQty));

                    batchDAO.update(batch);

                    IisOrderHasBatch iisOrderHasBatch = new IisOrderHasBatch(new IisOrderHasBatchPK());

                    iisOrderHasBatch.getIisOrderHasBatchPK().setBatch(batch.getId());
                    iisOrderHasBatch.getIisOrderHasBatchPK().setIisOrder(iisOrder.getIisOrderId());
                    iisOrderHasBatch.setIssuedQuantity(itemRemainingQty);

                    iisOrderHasBatchDAO.save(iisOrderHasBatch);

                    itemRemainingQty = BigDecimal.ZERO;
                    break;
                }
                else {
                    // itemRemainingQty > batch's remaining qty
                    // we get available qty from the batch and set batch remaining qty to zero
                    BigDecimal batchRemQty = batch.getRemainingQuantity();

                    itemRemainingQty.subtract(batchRemQty);
                    batch.setRemainingQuantity(BigDecimal.ZERO);
                    batchDAO.update(batch);


                    IisOrderHasBatch iisOrderHasBatch = new IisOrderHasBatch(new IisOrderHasBatchPK());

                    iisOrderHasBatch.getIisOrderHasBatchPK().setBatch(batch.getId());
                    iisOrderHasBatch.getIisOrderHasBatchPK().setIisOrder(iisOrder.getIisOrderId());

                    iisOrderHasBatch.setIssuedQuantity(batchRemQty);

                    iisOrderHasBatchDAO.save(iisOrderHasBatch);
                }
            }
        }

    }


    @Transactional
    public List<IisOrder> getAll(String empid) {


        return null;  //To change body of created methods use File | Settings | File Templates.
    }



    @Transactional
    public IisOrder getOpenOrder(String sales_person) {
        IisOrder  iisOrder=iisOrderDAO.getOpenOrder(sales_person);
        return iisOrder;
    }

    @Transactional
    public IisOrder getIisOrder(String iisorder_id) {
        return iisOrderDAO.get(Long.parseLong(iisorder_id));
    }

    public List<IisOrderHasBatch> getBatchList(String iisorder_id) {
        return iisOrderHasBatchDAO.getAllByIisOrder(iisorder_id);
    }


}
