package org.opensms.app.db.controller.impl;

import org.opensms.app.db.entity.Vendor;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/14/13
 * Time: 9:49 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class VendorDAOController extends AbstractDAOImpl<Vendor, Integer>{
    public VendorDAOController() {
        super(Vendor.class, Integer.class);
    }
}
