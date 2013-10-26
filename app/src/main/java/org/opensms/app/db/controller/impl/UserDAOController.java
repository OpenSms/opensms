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

    /**
     * Get Valid User Object From Given Object
     * Validate by username and password
     *
     * @param user
     * @return
     */
    public User login(User user) {
        Query query=getCurrentSession().createQuery("SELECT u FROM User u WHERE u.username=:username AND u.password=:password");
        query.setString("username",user.getUsername());
        query.setString("password",user.getPassword());
        return (User) query.uniqueResult();
    }
}
