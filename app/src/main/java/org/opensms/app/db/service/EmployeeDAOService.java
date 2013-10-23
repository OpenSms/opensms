package org.opensms.app.db.service;

import org.opensms.app.db.controller.impl.EmployeeDAOController;
import org.opensms.app.db.entity.Employee;
import org.opensms.app.db.entity.Role;
import org.opensms.app.db.utils.UserRoleDAOComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/16/13
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class EmployeeDAOService {
    @Autowired
    private EmployeeDAOController employeeDAOController;

    @Autowired
    private UserRoleDAOComponent userRoleDAOComponent;

    /**
     * Save employee with roles
     *
     * @param employee
     * @param roles
     */
    public void saveEmployee(Employee employee, List<Role> roles) {
        userRoleDAOComponent.assignRolesToUser(roles, employee.getUserId());
        employeeDAOController.save(employee);
    }
}
