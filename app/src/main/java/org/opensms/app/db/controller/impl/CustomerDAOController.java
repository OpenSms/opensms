package org.opensms.app.db.controller.impl;

import org.opensms.app.db.entity.Customer;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/14/13
 * Time: 9:29 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CustomerDAOController extends AbstractDAOImpl<Customer, Integer>{
    public CustomerDAOController() {
        super(Customer.class, Integer.class);
    }
}
