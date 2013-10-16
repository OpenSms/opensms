package org.opensms.app.db.service;

import org.opensms.app.db.controller.impl.EmployeeDAOController;
import org.opensms.app.db.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void saveEmployee(Employee employee) {
        employeeDAOController.save(employee);
    }
}
