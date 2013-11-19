package org.opensms.app.db.controller.impl;

import org.hibernate.*;
import org.hibernate.collection.internal.AbstractPersistentCollection;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.Type;
import org.hibernate.validator.util.IdentitySet;
import org.opensms.app.db.controller.AbstractDAO;
import org.opensms.app.db.entity.EntityInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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
    @Autowired
    private SessionFactory sessionFactory;

    public AbstractDAOImpl(Class<T> entityClass, Class<E> entityIdClass) {
        this.entityClass = entityClass;
        this.entityIdClass = entityIdClass;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void update(T entity) {
        Session session = getCurrentSession();
        // Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        //  transaction.commit();
        //session.close();
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
        //  Transaction transaction = session.beginTransaction();
        session.save(entity);
        // transaction.commit();
        // session.close();

        // System.out.println(entity.getId());

        return entity.getId();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public T get(E id) {
        return (T) getCurrentSession().get(entityClass, id);  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Detach an entity from the session as it would be if the session was closed.
     *
     * @param entity the hibernate entity.
     */
    public void detach(Object entity) {

        // Check for lazy-loading proxies
        if (entity instanceof HibernateProxy) {
            SessionImplementor sessionImplementor = ((HibernateProxy) entity)
                    .getHibernateLazyInitializer().getSession();
            if (sessionImplementor instanceof Session) {
                ((Session) sessionImplementor).evict(entity);
            }

            return;
        }

        // processing queue
        Queue<Object> entities = new LinkedList<Object>();
        Set<Object> processedEntities = new IdentitySet();

        entities.add(entity);

        while ((entity = entities.poll()) != null) {

            // Skip already processed entities
            if (processedEntities.contains(entity)) {
                continue;
            }

            ClassMetadata classMetadata = sessionFactory
                    .getAllClassMetadata().get(entity.getClass().getName());
            String[] propertyNames = classMetadata.getPropertyNames();
            Session session = null;

            // Iterate through all persistent properties
            for (String propertyName : propertyNames) {

                Object propertyValue = classMetadata.getPropertyValue(entity, propertyName);
                Type propertyType = classMetadata.getPropertyType(propertyName);

                // Handle entity types
                if (propertyType.isEntityType()) {

                    // Detach proxies
                    if (propertyValue instanceof HibernateProxy) {
                        SessionImplementor sessionImplementor = ((HibernateProxy) propertyValue)
                                .getHibernateLazyInitializer().getSession();

                        if (sessionImplementor instanceof Session) {
                            ((Session) sessionImplementor).evict(propertyValue);

                            // If we don't yet have a session for this entity save it for later use.
                            if (session == null)
                                session = (Session) sessionImplementor;
                        }

                    } else {
                        // Add entities to the processing queue.
                        entities.add(propertyValue);
                    }

                }
                // Handle collection types
                else if (propertyType.isCollectionType()) {

                    if (propertyValue instanceof AbstractPersistentCollection) {
                        SessionImplementor sessionImplementor =
                                ((AbstractPersistentCollection) propertyValue).getSession();

                        // If we don't yet have a session for this entity save it for later use.
                        if (sessionImplementor instanceof Session && session == null) {
                            session = (Session) sessionImplementor;
                        }
                    }
                }
            }

            // Evict the entity and associated collections.
            if (session != null) {
                session.evict(entity);
            }

            processedEntities.add(entity);
        }
    }
}
