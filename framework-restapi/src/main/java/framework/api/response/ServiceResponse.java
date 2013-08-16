package framework.api.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceResponse<T> {

    public static <T> ServiceResponseBuilder<T> results() {
        final ServiceResponseBuilder<T> serviceResponseBuilder = new ServiceResponseBuilder<T>();
        return serviceResponseBuilder;
    }

    public static <T> ServiceResponseBuilder<T> results(List<T> results) {
        final ServiceResponseBuilder<T> serviceResponseBuilder = new ServiceResponseBuilder<T>();
        serviceResponseBuilder.results(results);
        return serviceResponseBuilder;
    }

    @XmlElement
    private final ResponseHeader responseHeader;

    @XmlElement
    private final List<T> results;

    protected ServiceResponse(ResponseHeader responseHeader, List<T> results) {
        this.responseHeader = responseHeader;
        this.results = results;
    }
}
