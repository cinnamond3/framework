package framework.core.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="USERGROUP")
public class Usergroup extends AbstractEntity {

	private static final long serialVersionUID = -1885738464094155147L;

	@Column
	private String name;
	
	@Column
	private String description;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Role> roles;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<Role> getRoles() {
		return roles;
	}

}
