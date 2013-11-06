package org.opensms.app.db.controller.impl;

import org.hibernate.Query;
import org.opensms.app.db.entity.UserRole;
import org.opensms.app.db.entity.UserRolePK;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/14/13
 * Time: 10:09 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserRoleDAOController extends AbstractDAOImpl<UserRole, UserRolePK> {
    public UserRoleDAOController() {
        super(UserRole.class, UserRolePK.class);
    }


    public List<UserRole> getUserRoles(Integer userId) {
        Query query = getCurrentSession().getNamedQuery("UserRole.findByUser");
        query.setString("user", userId.toString());

        return query.list();
    }

    /**
     * Update user roles
     *
     * @param userRoles
     */
    public void updateUserRoles(List<UserRole> userRoles) {
        for (UserRole userRole : userRoles) {
            update(userRole);
        }
    }
}
