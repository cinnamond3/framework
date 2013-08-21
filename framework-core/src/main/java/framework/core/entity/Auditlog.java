package framework.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import framework.core.enums.EventType;

/**
 * Represents events necessary for auditing.
 * 
 * @author Frederick Yap
 */
@Entity
@Table(name = "AUDITLOG")
@NamedQueries(value = { @NamedQuery(name = "findLastAuditlogByDetail", query = "from Auditlog where detail LIKE :detail ORDER BY logdate DESC") })
public class Auditlog extends AbstractEntity {

    private static final long serialVersionUID = -3935171119789690953L;

    @Column(columnDefinition = "TEXT")
    private String detail;

    @Column
    private Long logdate;

    @Column(columnDefinition = "TEXT")
    private String previous;

    @Column
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column
    private String userid;

    /**
     * Returns the event log entry.
     * 
     * @return the event log entry.
     */
    public String getDetail() {
        return this.detail;
    }

    /**
     * Returns the date the event has occured.
     * 
     * @return the date of the event.
     */
    public Long getLogdate() {
        return this.logdate;
    }

    /**
     * Returns the previous log entry if the event type is UPDATE.
     * 
     * @return the previous log entry.
     */
    public String getPrevious() {
        return this.previous;
    }

    /**
     * Returns the event type.
     * 
     * @return the event type.
     */
    public EventType getType() {
        return this.type;
    }

    /**
     * Returns the unique id of the authenticated user when the event has occured. Otherwise, returns SYSTEM if it is an
     * application event.
     * 
     * @return the unique id of the authenticated user.
     */
    public String getUserid() {
        return this.userid;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setLogdate(Long logdate) {
        this.logdate = logdate;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

}
