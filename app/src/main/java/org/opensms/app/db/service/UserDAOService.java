package org.opensms.app.db.service;

import org.opensms.app.db.controller.impl.RoleDAOController;
import org.opensms.app.db.controller.impl.UserDAOController;
import org.opensms.app.db.entity.Role;
import org.opensms.app.db.entity.User;
import org.opensms.app.db.utils.UserRoleDAOComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/9/13
 * Time: 5:16 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class UserDAOService {
    @Autowired
    private UserDAOController userDAOController;

    @Autowired
    private RoleDAOController roleDAOController;

    @Autowired
    private UserRoleDAOComponent userRoleDAOComponent;

    public Integer saveUser(User user) {
        user.setCreatedate(Calendar.getInstance().getTime());
        return userDAOController.save(user);
    }

    public List<Role> getAllRoles() {
        return roleDAOController.getAll();
    }

    public List<User> getAll() {
        return userDAOController.getAll();
    }

    public List<User> search(String query) {
        return userDAOController.search(query);
    }

    public User login(User user) {
        return userDAOController.login(user);
    }
}
