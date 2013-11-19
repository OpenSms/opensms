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
public class UserDAOController extends AbstractDAOImpl<User, Integer> {
    public UserDAOController() {
        super(User.class, Integer.class);
    }

    /**
     * Search user  by user id, username, country etc
     *
     * @param queryString
     * @return User object list
     */
    public List<User> search(String queryString) {
        String queryIdString = queryString;
        queryString = "%" + queryString + "%";

        Query query = getCurrentSession().createQuery("SELECT u FROM User u, UserContactDetail c WHERE " +
                "u.userId = :queryIdString OR u.username LIKE :queryString OR " +
                "c.name LIKE :queryString OR c.email LIKE :queryString OR " +
                "c.city LIKE :queryString OR c.country LIKE :queryString GROUP BY u.userId");

        query.setString("queryIdString", queryIdString);
        query.setString("queryString", queryString);

        return query.list();
    }

    /**
     * Update user account state
     *
     * @param user
     */
    public void updateUserAccountState(User user) {
        boolean accountStatus = user.getAccountStatus();

        Query query = getCurrentSession().createQuery("UPDATE User SET accountStatus = :accountStatus");
        query.setBoolean("accountStatus", accountStatus);

        query.executeUpdate();
    }

    /**
     * Validate user password
     *
     * @param user
     * @return
     */
    public boolean validatePassword(User user) {
        Query query = getCurrentSession().createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password");
        query.setString("username", user.getUsername());
        query.setString("password", user.getPassword());


        return query.uniqueResult() != null;  //To change body of created methods use File | Settings | File Templates.
    }

    /**
     * Change password
     *
     * @param user
     */
    public void changePassword(User user) {
        Query query = getCurrentSession().createQuery("UPDATE User SET password = :password");
        query.setString("password", user.getPassword());

        query.executeUpdate();
    }

    /**
     * Get Valid User Object From Given Object
     * Validate by username and password
     *
     * @param user
     * @return
     */
    public User login(User user) {
        Query query = getCurrentSession().createQuery("SELECT u FROM User u WHERE u.username=:username AND u.password=:password");
        query.setString("username", user.getUsername());
        query.setString("password", user.getPassword());
        return (User) query.uniqueResult();
    }

    public List<User> search(String queryString, String type) {

        System.out.println(queryString);
        System.out.println(type);

        String queryIdString = queryString;
        queryString = "%" + queryString + "%";

        Query query = null;
        if (type.equals("vendor")) {
            query = getCurrentSession().createQuery("SELECT u FROM Vendor u WHERE u.name LIKE  :queryString");

        }
        else if (type.equals("customer")) {
            query = getCurrentSession().createQuery("SELECT u FROM Customer u, UserContactDetail c WHERE " +
                    "u.userId = :queryIdString OR u.user.username LIKE :queryString OR " +
                    "c.name LIKE :queryString OR c.email LIKE :queryString OR " +
                    "c.city LIKE :queryString OR c.country LIKE :queryString GROUP BY u.userId");


            query.setString("queryIdString", queryIdString);
        }
        else {
            query = getCurrentSession().createQuery("SELECT u FROM User u, UserContactDetail c, UserRole  r WHERE " +
                    "(u.userId = :queryIdString OR u.username LIKE :queryString OR " +
                    "c.name LIKE :queryString OR c.email LIKE :queryString) " +
                    "AND r.role1.description = :type " +
                    "GROUP BY u.userId");

            query.setString("queryIdString", queryIdString);
            query.setString("type", type);
        }

       // query.setString("queryIdString", queryIdString);
        query.setString("queryString", queryString);

        return query.list();
    }
}
