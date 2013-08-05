package framework.core.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import framework.core.entity.listeners.EntityListener;

/**
 * This class provides basic implementations for all Entity classes.
 * 
 * @author Frederick Yap
 */
@MappedSuperclass
@EntityListeners(value = { EntityListener.class })
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = -2688529713266301979L;

    @Column
    private boolean deleted;

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    @Column
    private Long id;

    @Column
    @Version
    private Long version;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Customer customer;
    
    /**
     * Returns the unique identifier for this data.
     * 
     * @return the unique identifier.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Returns the version of this data used for pessimistic locking.
     * 
     * @return the version of this data.
     */
    public Long getVersion() {
        return this.version;
    }

    /**
     * Returns the status of this data.
     * 
     * @return true if this is marked as deleted.
     */
    public boolean isDeleted() {
        return this.deleted;
    }

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
}
