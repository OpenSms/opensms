package org.opensms.app.db.controller.impl;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.opensms.app.db.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/13/13
 * Time: 2:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class RoleDAOController extends AbstractDAOImpl<Role, Integer> {
    public RoleDAOController() {
        super(Role.class, Integer.class);
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = super.getAll();

        for (Role role : roles) {
            Hibernate.initialize(role);
            // Hibernate.initialize(role.getUserRoleList());
        }

        return roles;
    }

    public Role getByRole(String roleDescription) {
        Query query = getCurrentSession().createQuery("SELECT r FROM Role r WHERE r.description=:description");
        query.setString("description", roleDescription);

        return (Role) query.uniqueResult();
    }

    public Role getByRole(Integer roleId) {
        Query query = getCurrentSession().createQuery("SELECT r FROM Role r WHERE r.roleId=:roleId");
        query.setInteger("roleId", roleId);

        return (Role) query.uniqueResult();
    }
}
