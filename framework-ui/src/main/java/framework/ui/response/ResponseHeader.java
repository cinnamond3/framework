package framework.ui.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseHeader", propOrder = { "statusCode", "statusMessage" })
public class ResponseHeader {

    @XmlElement
    private Integer statusCode;

    @XmlElement
    private String statusMessage;

    /**
     * @return the statusCode
     */
    public Integer getStatusCode() {
        return this.statusCode;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return this.statusMessage;
    }

    /**
     * @param statusCode
     *            the statusCode to set
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @param statusMessage
     *            the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

}
