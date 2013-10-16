package org.opensms.app.db.controller.impl;

import org.opensms.app.db.entity.UserContactDetail;
import org.springframework.stereotype.Repository;

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
}
