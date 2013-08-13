package framework.support.utilities;

import java.io.InputStream;
import java.util.List;

import javax.inject.Named;

import com.thoughtworks.xstream.XStream;

import framework.core.utilities.XMLEncoder;

@Named
public class XMLEncoderImpl implements XMLEncoder {

    @SuppressWarnings("unchecked")
    @Override
    public <T> T convert(InputStream xml, Class<?>... type) {
        final XStream xStream = new XStream();
        for (final Class<?> aClass : type) {
            xStream.alias(aClass.getSimpleName(), aClass);
        }
        xStream.alias("List", List.class);
        xStream.alias("list", List.class);
        return (T) xStream.fromXML(xml);
    }

}
