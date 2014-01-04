package org.opensms.app.db.controller;

import org.opensms.app.db.entity.Batch;
import org.opensms.app.db.entity.Item;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 11/15/13
 * Time: 12:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BatchDAO extends AbstractDAO<Batch, String>{
    List<Batch> getBatchesWithItemId(Item item);

    /**
     * Get Max available item quantity
     *
     * @param item
     * @return
     */
    BigDecimal getMaxItemCount(String item);
}
