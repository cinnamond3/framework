package framework.ui.databeans;

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
    protected Integer getStatusCode() {
        return this.statusCode;
    }

    /**
     * @return the statusMessage
     */
    protected String getStatusMessage() {
        return this.statusMessage;
    }

    /**
     * @param statusCode
     *            the statusCode to set
     */
    protected void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @param statusMessage
     *            the statusMessage to set
     */
    protected void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

}
