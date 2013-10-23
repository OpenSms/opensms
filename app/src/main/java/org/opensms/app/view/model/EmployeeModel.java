package org.opensms.app.view.model;

import org.opensms.app.db.entity.Employee;
import org.opensms.app.db.entity.Role;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/19/13
 * Time: 9:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeModel {

    private Employee employee;
    private List<Role> roles;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
