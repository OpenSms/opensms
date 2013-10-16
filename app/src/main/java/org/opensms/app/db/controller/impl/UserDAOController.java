package org.opensms.app.db.controller.impl;

import org.opensms.app.db.entity.User;
import org.springframework.stereotype.Repository;

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
}
