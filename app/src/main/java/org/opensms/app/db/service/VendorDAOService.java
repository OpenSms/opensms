package org.opensms.app.db.service;

import org.opensms.app.db.controller.impl.RoleDAOController;
import org.opensms.app.db.controller.impl.UserRoleDAOController;
import org.opensms.app.db.controller.impl.VendorDAOController;
import org.opensms.app.db.entity.Role;
import org.opensms.app.db.entity.UserRole;
import org.opensms.app.db.entity.UserRolePK;
import org.opensms.app.db.entity.Vendor;
import org.opensms.app.db.utils.UserRoleDAOComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/14/13
 * Time: 9:50 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class VendorDAOService {
    @Autowired
    private VendorDAOController vendorDAOController;

    @Autowired
    private UserRoleDAOComponent userRoleDAOComponent;

    @Autowired RoleDAOController roleDAOController;

    /**
     * Save vendor
     *
     * @param vendor
     */
    public void save(Vendor vendor) {
        //Assign 'Vendor' Role to vendor
        userRoleDAOComponent.assignRoleToUser(roleDAOController.getByRole("vendor"), vendor.getUserId());

        vendorDAOController.save(vendor);
    }

    public Vendor getVendor(Integer userId) {
        return vendorDAOController.get(userId);
    }
}
