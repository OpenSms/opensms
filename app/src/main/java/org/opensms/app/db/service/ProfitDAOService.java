package org.opensms.app.db.service;

import org.opensms.app.db.controller.impl.ProfitDAOController;
import org.opensms.app.db.entity.Profit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/24/13
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class ProfitDAOService {

    @Autowired
    private ProfitDAOController profitDAOController;

    public void saveProfit(Profit profit) {
        profitDAOController.save(profit);
    }
}
