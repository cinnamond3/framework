package framework.core.persistence.impl;

import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import framework.core.entity.AbstractEntity;
import framework.core.persistence.Dao;
import framework.core.utilities.PropertiesUtil;

/**
 * This class contains basic CRUD implementation. All data access object classes must extends this class.
 * 
 * @author Frederick Yap
 * @param <T>
 *            must be an instance of {@link AbstractEntity}.
 */
@Named
public abstract class AbstractDao<T extends AbstractEntity> implements Dao<T> {

    private static final long serialVersionUID = 4419440269704730290L;

    private final Class<T> persistentClass;

    private PropertiesUtil propertiesUtil;

    protected EntityManager entityManager;

    /**
     * Default constructor.
     */
    @SuppressWarnings("unchecked")
    protected AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    /*
     * (non-Javadoc)
     * @see framework.core.persistence.Dao#delete(framework.core.entity.AbstractEntity)
     */
    @Override
    public void delete(T t) {
        this.entityManager.remove(t);
    }

    /*
     * (non-Javadoc)
     * @see framework.core.persistence.Dao#findById(java.lang.String)
     */
    @Override
    public T findById(String id) {
        return this.entityManager.find(this.persistentClass, id);
    }

    /*
     * (non-Javadoc)
     * @see framework.core.persistence.Dao#saveOrUpdate(framework.core.entity.AbstractEntity)
     */
    @Override
    public void saveOrUpdate(T t) {
        this.entityManager.merge(t);
    }

    /**
     * @param propertiesUtil
     *            the propertiesUtil to set
     */
    @Inject
    public void setPropertiesUtil(PropertiesUtil propertiesUtil) {
        this.propertiesUtil = propertiesUtil;
    }

    /**
     * Returns an instance of a cacheable named {@link Query}.
     * 
     * @param name
     *            the name of the query.
     * @return an instance of a named {@link Query}.
     */
    protected Query createCacheableNamedQuery(String name) {
        final Query query = this.createNamedQuery(name);
        query.setHint(this.propertiesUtil.getProperty("framework.cacheable.hint"), true);
        return query;
    }

    /**
     * Returns an instance of a named {@link Query}.
     * 
     * @param name
     *            the name of the query.
     * @return an instance of a named {@link Query}.
     */
    protected Query createNamedQuery(String name) {
        final Query query = this.entityManager.createNamedQuery(name);
        return query;
    }

    /**
     * Remove the given entity from the persistence context, causing a managed entity to become detached. Unflushed
     * changes made to the entity if any (including removal of the entity), will not be synchronized to the database.
     * Entities which previously referenced the detached entity will continue to reference it.
     * 
     * @param t
     *            entity instance.
     */
    protected void detach(T t) {
        this.entityManager.detach(t);
    }

    /**
     * Sets an instance of {@link EntityManager} to be used by the data access object class. Override this method only
     * if using a different persistence unit aside from <em>defaultDb</em>.<br/>
     * <br/>
     * <em>WARNING!</em> This method must not be called directly.
     * 
     * @param entityManager
     *            instance of {@link EntityManager}.
     */
    @PersistenceContext
    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
