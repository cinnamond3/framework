package framework.core.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = -2688529713266301979L;

	@Id
	@Column
	private String id;
	
	@Column
	private boolean deleted;
	
	@Column
	@Version
	private Long version;

	protected AbstractEntity() {
		this.id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public Long getVersion() {
		return version;
	}
	
}
