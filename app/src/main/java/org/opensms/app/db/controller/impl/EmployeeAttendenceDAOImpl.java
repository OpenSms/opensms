package org.opensms.app.db.controller.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.opensms.app.db.controller.EmployeeAttendenceDAO;
import org.opensms.app.db.entity.EmployeeAttendence;
import org.opensms.app.db.entity.EmployeeAttendencePK;
import org.opensms.app.db.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

/**
 * Created by sadika on 1/7/14.
 */
@Repository
public class EmployeeAttendenceDAOImpl extends AbstractDAOImpl<EmployeeAttendence, EmployeeAttendencePK> implements EmployeeAttendenceDAO {

    public EmployeeAttendenceDAOImpl() {
        super(EmployeeAttendence.class, EmployeeAttendencePK.class);
    }

    @Override
    public boolean leave(User u) {

        Session session = getCurrentSession();

        Query query = session.createQuery("select e from EmployeeAttendence e where e.leaveTime is null and e.employeeAttendencePK.userId = :userId");
        query.setInteger("userId", u.getUserId());

        List<EmployeeAttendence> list = query.list();

        if (list.isEmpty())
            return false;

        EmployeeAttendence e = list.get(0);
        e.setLeaveTime(Calendar.getInstance().getTime());

        update(e);

        return (e != null);
    }
}
