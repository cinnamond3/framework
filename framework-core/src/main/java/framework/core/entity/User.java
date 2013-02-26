package framework.core.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User extends AbstractEntity {

	private static final long serialVersionUID = -7767487387897790096L;

	@Column(unique=true, nullable=false)
	private String username;
	
	@Column
	private String password;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Usergroup usergroup;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Userdetails userdetails;
	
	@Column
	private Long profileexpiration;
	
	@Column
	private Long passwordexpiration;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Usergroup getUsergroup() {
		return usergroup;
	}

	public Userdetails getUserdetails() {
		return userdetails;
	}

	public Long getProfileexpiration() {
		return profileexpiration;
	}

	public Long getPasswordexpiration() {
		return passwordexpiration;
	}

}
