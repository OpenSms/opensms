package org.opensms.app.db.controller;

import org.opensms.app.db.entity.GrnOrder;
import org.opensms.app.db.entity.Vendor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 11/15/13
 * Time: 12:34 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GrnOrderDAO extends AbstractDAO<GrnOrder,Long>{
    List<GrnOrder> getAllGrnOrdersOfCurrentVendor(Vendor vendor);
}
