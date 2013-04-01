package framework.ui.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceResponse<T> {

    @XmlElement
    private ResponseHeader responseHeader;

    @XmlElement
    private List<T> results;

    /**
     * @return the responseHeader
     */
    public ResponseHeader getResponseHeader() {
        return this.responseHeader;
    }

    /**
     * @return the results
     */
    public List<T> getResults() {
        return this.results;
    }

    /**
     * @param responseHeader
     *            the responseHeader to set
     */
    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    /**
     * @param results
     *            the results to set
     */
    public void setResults(List<T> results) {
        this.results = results;
    }

}
