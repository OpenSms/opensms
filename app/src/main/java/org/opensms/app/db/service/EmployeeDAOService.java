package org.opensms.app.db.service;

import org.opensms.app.db.controller.EmployeeAttendenceDAO;
import org.opensms.app.db.controller.impl.EmployeeDAOController;
import org.opensms.app.db.controller.impl.UserDAOController;
import org.opensms.app.db.controller.impl.UserRoleDAOController;
import org.opensms.app.db.entity.*;
import org.opensms.app.db.utils.UserRoleDAOComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
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

    @Autowired
    private UserRoleDAOController userRoleDAOController;

    @Autowired
    private UserDAOController userDAOController;

    @Autowired
    private EmployeeAttendenceDAO employeeAttendenceDAO;

    /**
     * Save employee with roles
     *
     * @param employee
     * @param roles
     */
    public void saveEmployee(Employee employee, List<Role> roles) {
        userRoleDAOComponent.assignRolesToEmployee(roles, employee.getUserId());
        employeeDAOController.save(employee);
    }

    public Employee getEmployee(Integer userId) {
        return employeeDAOController.get(userId);
    }

    public void updateEmployee(Employee employee) {
        employeeDAOController.update(employee);
    }

    public void updateEmployeeRoles(List<UserRole> userRoles) {
        userRoleDAOController.updateUserRoles(userRoles);
    }

    public boolean attendanceSignin(User user) {

        User u = userDAOController.getUserByUserName(user.getUsername());

        if (u == null)
            return false;

        if (!u.getPassword().equals(user.getPassword())) {
            return false;
        }

        EmployeeAttendencePK eap = new EmployeeAttendencePK();
        eap.setUserId(u.getUserId());
        eap.setSigninTime(Calendar.getInstance().getTime());

        EmployeeAttendence ea = new EmployeeAttendence();
        ea.setEmployeeAttendencePK(eap);

        EmployeeAttendencePK ppp = employeeAttendenceDAO.save(ea);

        return (ppp != null);
    }

    public boolean attendanceLeave(User user) {

        User u = userDAOController.getUserByUserName(user.getUsername());

        if (u == null)
            return false;

        if (!u.getPassword().equals(user.getPassword())) {
            return false;
        }

        return employeeAttendenceDAO.leave(u);
    }

    public List<EmployeeAttendence> getAllEmployeeAttendance() {
        return employeeAttendenceDAO.getAll();
    }

}