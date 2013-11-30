package org.opensms.app.db.controller;

import org.opensms.app.db.entity.IisOrder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/16/13
 * Time: 1:06 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IisOrderDAO extends AbstractDAO<IisOrder, Long> {

    /**
     * GET all IIS Orders for related employee
     * @param empid
     * @return
     */
    List<IisOrder> getAll(Integer empid);
}
