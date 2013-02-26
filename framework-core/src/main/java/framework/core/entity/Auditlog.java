package framework.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name="AUDITLOG")
public class Auditlog extends AbstractEntity {

	private static final long serialVersionUID = -3935171119789690953L;

	public enum AuditlogType {
		DELETE, EXCEPTION, INSERT, LOGIN, LOGOUT, UPDATE
	}
	
	@Column
	@Enumerated(EnumType.STRING)
	private AuditlogType type;
	
	@Column(columnDefinition="TEXT")
	private String detail;
	
	@Column(columnDefinition="TEXT")
	private String previous;
	
	@Column
	private Long logdate;
	
	@Column
	private String userid;

	public AuditlogType getType() {
		return type;
	}

	public String getDetail() {
		return detail;
	}

	public String getPrevious() {
		return previous;
	}

	public Long getLogdate() {
		return logdate;
	}

	public String getUserid() {
		return userid;
	}
	
}
