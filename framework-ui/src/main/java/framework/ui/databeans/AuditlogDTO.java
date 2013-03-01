package framework.ui.databeans;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AuditlogDTO extends AbstractDTO {

    @XmlElement
    private String detail;

    @XmlElement
    private Date logdate;

    @XmlElement
    private String previous;

    @XmlElement
    private String type;

    @XmlElement
    private String userid;

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return the logdate
     */
    public Date getLogdate() {
        return logdate;
    }

    /**
     * @param logdate the logdate to set
     */
    public void setLogdate(Date logdate) {
        this.logdate = logdate;
    }

    /**
     * @return the previous
     */
    public String getPrevious() {
        return previous;
    }

    /**
     * @param previous the previous to set
     */
    public void setPrevious(String previous) {
        this.previous = previous;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    
    
}
