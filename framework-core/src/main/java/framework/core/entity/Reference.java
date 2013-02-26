package framework.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name="REFERENCE")
public class Reference extends AbstractEntity {

	private static final long serialVersionUID = -6616038947469958259L;

	public enum ReferenceType {
		
	}
	
	@Column
	private String code;
	
	@Column
	private String label;
	
	@Column
	@Enumerated(EnumType.STRING)
	private ReferenceType type;

	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	public ReferenceType getType() {
		return type;
	}
	
}
