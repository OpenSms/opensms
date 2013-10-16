package org.opensms.app.db.service;

import org.opensms.app.db.controller.impl.ContactDetailsDAOController;
import org.opensms.app.db.entity.UserContactDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/9/13
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class ContactDetailsDAOService {
    @Autowired
    private ContactDetailsDAOController contactDetailsDAOController;

    public void saveContactDetails(UserContactDetail contactDetail) {
        contactDetailsDAOController.save(contactDetail);
    }
}