package framework.extensions.utilities;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.utilities.PropertiesUtil;

@Named
public class PropertiesUtilImpl implements PropertiesUtil {

    private final Properties properties;

    @Inject
    protected PropertiesUtilImpl(Properties propertiesReader) {
        this.properties = propertiesReader;
    }

    @Override
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

}
