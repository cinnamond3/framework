package framework.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENT")
public class Client extends AbstractEntity {

	private static final long serialVersionUID = 2800740052878163935L;

	@Column(nullable=false, unique=true)
    private String name;
	
	@Column(nullable=false, unique=true)
    private Long validity;

	@Column(nullable=false, unique=true)
    private String emailaddress;
	
	@Column(nullable=false, unique=true)
    private String phone;
	
	@Column(nullable=false, unique=true)
    private String mobile;
	
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

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
