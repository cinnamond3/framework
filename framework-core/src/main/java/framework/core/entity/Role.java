package framework.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ROLE")
public class Role extends AbstractEntity {

	private static final long serialVersionUID = 1759977741752483761L;

	@Column
	private String name;
	
	@Column
	private String description;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
}
