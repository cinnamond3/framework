package framework.core.utilities;

/**
 * Utility for reading properties file.
 * 
 * @author Frederick Yap
 */
public interface PropertiesUtil {

    /**
     * Searches for the property with the specified key in this property list. If the key is not found in this property
     * list, the default property list, and its defaults, recursively, are then checked. The method returns null if the
     * property is not found.
     * 
     * @param key
     *            the property key.
     * @return the value in this property list with the specified key value.
     */
    String getProperty(String key);

}
