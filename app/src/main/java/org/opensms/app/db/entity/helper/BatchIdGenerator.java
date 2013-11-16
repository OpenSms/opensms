package org.opensms.app.db.entity.helper;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 11/15/13
 * Time: 4:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class BatchIdGenerator implements IdentifierGenerator {


    private static Logger log = Logger.getLogger(BatchIdGenerator.class);

    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        String prefix = "B";






        return UUID.randomUUID().toString();
    }
}
