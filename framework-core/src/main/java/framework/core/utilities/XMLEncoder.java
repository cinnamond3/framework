package framework.core.utilities;

import java.io.InputStream;

import framework.core.entity.AbstractEntity;
import framework.core.entity.Data;

/**
 * Utility for parsing XML to POJO. User must provide necessary implementation.
 * 
 * @author Frederick Yap
 */
public interface XMLEncoder {

    /**
     * Converts XML to {@link Data} class.
     * 
     * @param xml
     *            {@link InputStream} containing the XML file.
     * @param types
     *            types to be used as alias.
     * @return converted XML to {@link Data} class.
     */
    <T extends AbstractEntity> Data<T> convert(InputStream xml, Class<?>... types);

    /**
     * Converts XML to entity of type T.
     * 
     * @param xml
     *            {@link InputStream} containing the XML file.
     * @param type
     *            the return type.
     * @return converted XML to type T.
     */
    <T> T convertTo(InputStream xml, Class<T> type);
}
