package org.opensms.app.db.controller.impl;

import org.hibernate.Query;
import org.hibernate.Session;
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

    /**
     *
     *
     * Get customer from username
     *
     * @param query
     * @return
     */
    public Customer search(String query) {
        Session session = getCurrentSession();
        Query sessionQuery = session.createQuery("SELECT c FROM Customer c " +
                "WHERE c.userId= (SELECT u.userId FROM " +
                "User u WHERE u.username= :query)");
        sessionQuery.setString("query",query);
        return (Customer) sessionQuery.uniqueResult();
    }
}
