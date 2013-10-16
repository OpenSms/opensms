package org.opensms.app.db.controller.impl;

import org.opensms.app.db.entity.Employee;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/16/13
 * Time: 2:37 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class EmployeeDAOController extends AbstractDAOImpl<Employee, Integer>{
    public EmployeeDAOController() {
        super(Employee.class, Integer.class);
    }
}
