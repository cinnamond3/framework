package framework.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends AbstractEntity {

	private static final long serialVersionUID = 2800740052878163935L;

	private String name;
	
	private Long validity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getValidity() {
		return validity;
	}

	public void setValidity(Long validity) {
		this.validity = validity;
	}
	
	
}
