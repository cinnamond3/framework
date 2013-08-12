package framework.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.AbstractEntity;
import framework.core.persistence.Dao;
import framework.core.service.Service;

/**
 * Provides basic business operation for all service classes.
 * 
 * @author Frederick Yap
 * @param <T>
 *            must extends {@link AbstractEntity}.
 * @param <I>
 *            must extends {@link Dao} of type T.
 */
@Named
public abstract class AbstractService<T extends AbstractEntity, I extends Dao<T>> implements Service<T> {

    private static final long serialVersionUID = 8443877242673541465L;

    private I persistence;

    /*
     * (non-Javadoc)
     * @see framework.core.service.Service#delete(java.util.Collection)
     */
    @Override
    public void delete(List<T> ts) {
        for (T t : ts) {
            this.delete(t);
        }
    }

    /*
     * (non-Javadoc)
     * @see framework.core.service.Service#delete(T t)
     */
    @Override
    public void delete(T t) {
        this.persistence.delete(t);
    }

    /*
     * (non-Javadoc)
     * @see framework.core.service.Service#findById(java.lang.String)
     */
    @Override
    public T findById(String id) {
        return this.persistence.findById(id);
    }

    /*
     * (non-Javadoc)
     * @see framework.core.service.Service#saveOrUpdate(java.util.Collection)
     */
    @Override
    public List<T> saveOrUpdate(List<T> ts) {
        List<T> list = new ArrayList<T>();
        for (final T t : ts) {
            list.add(this.saveOrUpdate(t));
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * @see framework.core.service.Service#saveOrUpdate(T t)
     */
    @Override
    public T saveOrUpdate(T t) {
        return this.persistence.saveOrUpdate(t);
    }

    /**
     * Returns the data access class used by the service.
     * 
     * @return the data access class.
     */
    protected I getPersistence() {
        return this.persistence;
    }

    /**
     * Sets an instance of data access class to be used by the service. This is normally injected by the IOC container.
     * 
     * @param persistence
     *            instance of data access class.
     */
    @Inject
    protected void setPersistence(I persistence) {
        this.persistence = persistence;
    }

}
