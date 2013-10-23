package org.opensms.app.db.controller.impl;

import org.hibernate.Query;
import org.opensms.app.db.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/9/13
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDAOController extends AbstractDAOImpl<User, Integer>{
    public UserDAOController() {
        super(User.class, Integer.class);
    }

    public List<User> search(String queryString) {
        String queryIdString = queryString;
        queryString = "%" + queryString + "%";

        Query query = getCurrentSession().createQuery("SELECT u FROM User u WHERE u.userId = :queryIdString OR u.username LIKE :queryString");
        query.setString("queryIdString", queryIdString);
        query.setString("queryString", queryString);

        return query.list();
    }
}
