package org.opensms.app.db.service;

import org.apache.log4j.Logger;
import org.opensms.app.db.controller.BatchDAO;
import org.opensms.app.db.controller.GrnOrderDAO;
import org.opensms.app.db.entity.Batch;
import org.opensms.app.db.entity.GrnOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 11/15/13
 * Time: 12:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class GrnOrderDAOService {

    private static final Logger LOGGER=Logger.getLogger(GrnOrderDAOService.class);

    @Autowired
    private GrnOrderDAO grnOrderDAO;
    @Autowired
    private BatchDAO batchDAO;



    /**
     * Save Grn order
     *
     * @param grnOrder
     * @param batchList
     */
    public void save(GrnOrder grnOrder, List<Batch> batchList) {

        LOGGER.info(grnOrder);



        grnOrder.setBatchList(null);



        grnOrder.setReceiveDate(Calendar.getInstance().getTime());

        grnOrderDAO.save(grnOrder);


        for (Batch batch:batchList){


            batch.setGrnOrder(grnOrder);

            Calendar instance = Calendar.getInstance();
            instance.add(Calendar.MONTH,1);

            batch.setExpireDate(instance.getTime());
            batch.setRemainingQuantity(batch.getQuantity());
            batchDAO.save(batch);
        }

    }

    public GrnOrder getGrnOrder(Long grnOrderId) {
        return grnOrderDAO.get(grnOrderId);
    }
}
