package org.opensms.app.db.service;

import org.opensms.app.db.controller.IisOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/16/13
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class IisOrderDAOService {
    @Autowired
    private IisOrderDAO iisOrderDAO;
}
