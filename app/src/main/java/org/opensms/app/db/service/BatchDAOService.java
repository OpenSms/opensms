package org.opensms.app.db.service;

import org.opensms.app.db.controller.BatchDAO;
import org.opensms.app.db.controller.ItemDAO;
import org.opensms.app.db.entity.Batch;
import org.opensms.app.db.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dewmal on 1/4/14.
 */
@Service
public class BatchDAOService {

    @Autowired
    private BatchDAO batchDAO;

    @Autowired
    private ItemDAO itemDAO;


    @Transactional
    public BigDecimal getMaxItemCount(String item) {
        return batchDAO.getMaxItemCount(item);
    }

    @Transactional
    public List<Batch> getAll(String itemId) {
        return batchDAO.getBatchesWithItemId(itemDAO.get(itemId));
    }
}
