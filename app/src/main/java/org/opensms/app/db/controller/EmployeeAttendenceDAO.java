package org.opensms.app.db.controller;

import org.opensms.app.db.entity.EmployeeAttendence;
import org.opensms.app.db.entity.EmployeeAttendencePK;
import org.opensms.app.db.entity.User;

import java.util.List;

/**
 * Created by sadika on 1/7/14.
 */
public interface EmployeeAttendenceDAO extends AbstractDAO<EmployeeAttendence, EmployeeAttendencePK>  {
    boolean leave(User u);

    List<EmployeeAttendence> getCurrentEmployeeAttendance(Integer userId);
}
