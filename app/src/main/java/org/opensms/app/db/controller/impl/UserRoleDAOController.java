package org.opensms.app.db.controller.impl;

import org.opensms.app.db.entity.UserRole;
import org.opensms.app.db.entity.UserRolePK;
import org.springframework.stereotype.Repository;

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



}
