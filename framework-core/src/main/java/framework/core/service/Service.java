package framework.core.service;

import java.io.Serializable;
import java.util.Collection;

import framework.core.entity.AbstractEntity;

/**
 * This class contains basic business operations. All service classes must extend from this class.
 * 
 * @author Frederick Yap
 * @param <T>
 *            must extends {@link AbstractEntity}.
 */
public interface Service<T extends AbstractEntity> extends Serializable {

    /**
     * Delete persisted instances in the datastore.
     * 
     * @param ts
     *            persisted instances.
     */
    void delete(Collection<T> ts);

    /**
     * Delete persisted instances in the datastore.
     * 
     * @param ts
     *            persisted instances.
     */
    void delete(@SuppressWarnings("unchecked") T... ts);

    /**
     * Delete persisted instances in the datastore.
     * 
     * @param t
     *            persisted instances.
     */
    void delete(T t);

    /**
     * Return the persistent instance of the given entity class with the given identifier, or null if there is no such
     * persistent instance.
     * 
     * @param id
     *            the unique identifier of the entity class.
     * @return the persistent instance of the given entity class with the given identifier.
     */
    T findById(String id);

    /**
     * Either save or update the given instances of an entity to the datastore.
     * 
     * @param ts
     *            the persisted instances.
     */
    void saveOrUpdate(Collection<T> ts);

    /**
     * Either save or update the given instances of an entity to the datastore.
     * 
     * @param ts
     *            the persisted instances.
     */
    void saveOrUpdate(@SuppressWarnings("unchecked") T... ts);

    /**
     * Either save or update the given instances of an entity to the datastore.
     * 
     * @param t
     *            the persisted instances.
     */
    void saveOrUpdate(T t);
}
