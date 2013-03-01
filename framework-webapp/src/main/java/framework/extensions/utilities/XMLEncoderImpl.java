package framework.extensions.utilities;

import java.io.InputStream;

import javax.inject.Named;

import com.thoughtworks.xstream.XStream;

import framework.core.entity.AbstractEntity;
import framework.core.entity.Data;
import framework.core.utilities.XMLEncoder;

@Named
public class XMLEncoderImpl implements XMLEncoder {

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractEntity> Data<T> convert(InputStream xml, Class<?>... type) {
        final XStream xStream = new XStream();
        xStream.alias("Data", Data.class);
        for (Class<?> aClass : type) {
            xStream.alias(aClass.getSimpleName(), aClass);
        }
        return (Data<T>) xStream.fromXML(xml);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <T> T convertTo(InputStream xml, Class<T> type) {
        final XStream xStream = new XStream();
        xStream.alias(type.getSimpleName(), type);
        return (T) xStream.fromXML(xml);
    }

}
