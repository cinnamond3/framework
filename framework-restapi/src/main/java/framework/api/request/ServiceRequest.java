package framework.api.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServiceRequest implements Serializable {

    private static final long serialVersionUID = -4675056771193159091L;

    private RequestHeader requestHeader;

    public RequestHeader getRequestHeader() {
        return this.requestHeader;
    }

    public void setRequestHeader(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }
}
