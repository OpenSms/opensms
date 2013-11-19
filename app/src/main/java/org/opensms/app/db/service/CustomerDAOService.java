package org.opensms.app.db.service;

import org.opensms.app.db.controller.impl.CustomerDAOController;
import org.opensms.app.db.controller.impl.RoleDAOController;
import org.opensms.app.db.entity.Customer;
import org.opensms.app.db.entity.Role;
import org.opensms.app.db.utils.UserRoleDAOComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/14/13
 * Time: 9:33 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class CustomerDAOService {
    @Autowired
    private CustomerDAOController customerDAOController; //Customer DAO

    @Autowired
    private UserRoleDAOComponent userRoleDAOComponent;

    @Autowired
    private RoleDAOController roleDAOController;

    /**
     * Save customer with Roles
     *
     * @param customer
     */
    public void saveCustomer(Customer customer) {
        //Assign 'Customer' Role to customer
        userRoleDAOComponent.assignRoleToUser(roleDAOController.getByRole("customer"), customer.getUserId());

        //Save customer into db
        customerDAOController.save(customer);
    }

    public Customer getCustomer(Integer customerId) {
        return customerDAOController.get(customerId);
    }
}