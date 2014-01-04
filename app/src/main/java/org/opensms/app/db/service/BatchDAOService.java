package org.opensms.app.db.service;

import org.opensms.app.db.controller.BatchDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by dewmal on 1/4/14.
 */
@Service
public class BatchDAOService {

    @Autowired
    private BatchDAO batchDAO;


    @Transactional
    public BigDecimal getMaxItemCount(String item) {
        return batchDAO.getMaxItemCount(item);
    }
}
