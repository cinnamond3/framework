package framework.core.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Represents the usergroup that a user can be a member-of.
 * 
 * @author Frederick Yap
 */
@Entity
@Table(name = "USERGROUP")
public class Usergroup extends AbstractEntity {

    private static final long serialVersionUID = -1885738464094155147L;

    @Column
    private String description;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Role> roles;

    /**
     * Returns the description of this usergroup.
     * 
     * @return the description of this usergroup.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the unique name of this usergroup.
     * 
     * @return the unique name of this usergroup.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the {@link Role}s that this usergroup have access to.
     * 
     * @return the {@link Role}s accessible to this usergroup.
     */
    public List<Role> getRoles() {
        return this.roles;
    }

}
