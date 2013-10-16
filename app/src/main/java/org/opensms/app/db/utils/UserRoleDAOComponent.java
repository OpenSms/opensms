package org.opensms.app.db.utils;

import org.opensms.app.db.controller.impl.RoleDAOController;
import org.opensms.app.db.controller.impl.UserRoleDAOController;
import org.opensms.app.db.entity.Role;
import org.opensms.app.db.entity.UserRole;
import org.opensms.app.db.entity.UserRolePK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/14/13
 * Time: 10:53 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class UserRoleDAOComponent {
    @Autowired
    private RoleDAOController roleDAOController; // Role DAO

    @Autowired
    private UserRoleDAOController userRoleDAOController; //User Role DAO

    /**
     * Assign single role to user
     *
     * @param role
     * @param userId
     */
    public void assignRoleToUser(Role role, Integer userId) {
        //  If role does not exists on database add it
        if (roleDAOController.getByRole(role.getDescription()) == null) {
            roleDAOController.save(role);
        }

        // assigning role to user
        UserRolePK userRolePK = new UserRolePK(role.getRoleId(), userId, Calendar.getInstance().getTime());
        UserRole userRole = new UserRole(userRolePK);
        userRole.setActive(true);

        userRoleDAOController.save(userRole);
    }

    /**
     * Assign multiple roles to user
     *
     * @param roles
     * @param userId
     */
    public void assignRolesToUser(List<Role> roles, Integer userId) {
        //  If role does not exists on database add it
        for (Role role : roles) {
            assignRoleToUser(role, userId);
        }
    }
}
