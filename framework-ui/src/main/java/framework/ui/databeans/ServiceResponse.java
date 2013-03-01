package framework.ui.databeans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceResponse<T extends AbstractDTO> {

    @XmlElement
    private ResponseHeader responseHeader;

    @XmlElement
    private T[] results;

    /**
     * @return the responseHeader
     */
    protected ResponseHeader getResponseHeader() {
        return this.responseHeader;
    }

    /**
     * @return the results
     */
    protected T[] getResults() {
        return this.results;
    }

    /**
     * @param responseHeader
     *            the responseHeader to set
     */
    protected void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    /**
     * @param results
     *            the results to set
     */
    protected void setResults(@SuppressWarnings("unchecked") T... results) {
        this.results = results;
    }

}
