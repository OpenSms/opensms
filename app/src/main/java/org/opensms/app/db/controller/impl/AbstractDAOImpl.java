package org.opensms.app.db.controller.impl;

import org.opensms.app.db.controller.AbstractDAO;
import org.hibernate.*;
import org.opensms.app.db.entity.EntityInterface;
import org.opensms.app.db.entity.UserRole;
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
public abstract class AbstractDAOImpl<T extends EntityInterface<E>, E extends Serializable> implements AbstractDAO<T, E> {


    private Class<T> entityClass;
    private Class<E> entityIdClass;

    public AbstractDAOImpl(Class<T> entityClass, Class<E> entityIdClass) {
        this.entityClass = entityClass;
        this.entityIdClass = entityIdClass;
    }

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    @Override
    public void update(T entity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
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

    @Override
    public E save(T entity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();

        System.out.println(entity.getId());

        return entity.getId();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public T get(E id) {
        return (T) getCurrentSession().get(entityClass, id);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
