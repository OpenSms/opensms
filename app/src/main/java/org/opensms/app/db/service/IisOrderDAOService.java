package org.opensms.app.db.service;

import org.opensms.app.db.controller.BatchDAO;
import org.opensms.app.db.controller.IisOrderDAO;
import org.opensms.app.view.model.IisOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

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

    public void saveIisOrder(IisOrderModel iisOrderModel) {
        iisOrderModel.getIisOrder().setIssOrderDateTime(Calendar.getInstance().getTime());

        iisOrderDAO.save(iisOrderModel.getIisOrder());

        iisOrderModel.getIisOrder().getIisOrderId();

    }
}
