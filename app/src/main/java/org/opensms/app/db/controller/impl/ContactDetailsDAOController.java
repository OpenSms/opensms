package org.opensms.app.db.controller.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.opensms.app.db.entity.UserContactDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/9/13
 * Time: 5:43 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ContactDetailsDAOController extends AbstractDAOImpl<UserContactDetail, Integer> {

    public ContactDetailsDAOController() {
        super(UserContactDetail.class, Integer.class);
    }

    /**
     * Search Contact Details when user id is given.
     *
     * @param queryString
     * @return
     */
    public List<UserContactDetail> search(String queryString) {
        Query query = getCurrentSession().createQuery("SELECT u FROM UserContactDetail u WHERE u.userId = :queryString");
        query.setString("queryString", queryString);

        return query.list();
    }
}
