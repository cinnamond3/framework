package framework.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="USERDETAILS")
public class Userdetails extends AbstractEntity {

	private static final long serialVersionUID = 5342989782721883971L;

	@Column
	private String fullname;
	
	@Column
	private Long birthdate;
	
	@Column
	private String address;
	
	@Column
	private String city;
	
	@Column
	private String country;
	
	@Column
	private String emailaddress;
	
	@Column
	private Long zipcode;
	
	@Column
	private String phone;

	public String getFullname() {
		return fullname;
	}

	public Long getBirthdate() {
		return birthdate;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public Long getZipcode() {
		return zipcode;
	}

	public String getPhone() {
		return phone;
	}
	
}
