package framework.api.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SystemParameterResponse implements Serializable {

    private static final long serialVersionUID = 908256462946326444L;

    @XmlElement
    private String code;

    @XmlElement
    private String description;

    @XmlElement
    private String maximum;

    @XmlElement
    private String minimum;

    @XmlElement
    private String readonly;

    @XmlElement
    private String type;

    @XmlElement
    private String value;

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public String getMaximum() {
        return this.maximum;
    }

    public String getMinimum() {
        return this.minimum;
    }

    public String getReadonly() {
        return this.readonly;
    }

    public String getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
