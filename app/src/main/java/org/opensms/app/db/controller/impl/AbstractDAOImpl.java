package org.opensms.app.db.controller.impl;

import org.opensms.app.db.controller.AbstractDAO;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dewmalpc
 * Date: 6/21/13
 * Time: 1:18 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDAOImpl<T, E extends Serializable> implements AbstractDAO<T, E> {


    private Class<T> entityClass;

    public AbstractDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void update(T entity) {
        getCurrentSession().saveOrUpdate(entity);
    }


    @Override
    public List<T> getAll() {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        List<T> list = criteria.list();
        for (T entiy : list) {
            Hibernate.initialize(entiy);
        }
        return list; 
    }

    @Override
    public List<T> retriveQuery(Class<T> entityClass, String squery) {
        Query query = getCurrentSession().createQuery(squery);
        return query.list();

    }


}
