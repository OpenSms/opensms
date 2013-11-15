package org.opensms.app.db.controller;

import org.opensms.app.db.entity.Item;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 11/6/13
 * Time: 10:13 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ItemDAO extends AbstractDAO<Item, String> {

    /**
     * Retrive All Items realated with name hint
     *
     * @param hint
     * @return
     */
    List<Item> getAll(String hint);
}
