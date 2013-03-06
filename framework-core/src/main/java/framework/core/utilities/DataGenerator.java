package framework.core.utilities;

import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.AbstractEntity;
import framework.core.entity.Data;

/**
 * Base class for data generator classes.
 * 
 * @author Frederick Yap
 */
@Named
public abstract class DataGenerator {

    private XMLEncoder xmlEncoder;

    /**
     * Returns the new database version. Normally, the database version is supplied by <em>DBVersion{}.data</em> file.
     * However, for some cases, it is necessary to override this method especially if no <em>DBVersion{}.data</em> is
     * provided.
     * 
     * @return the new database version.
     */
    protected abstract Integer getDBVersion();

    /**
     * Override this method to perform additional tasks.
     */
    protected abstract void performDataOperation();

    /**
     * Initialize this class.
     */
    protected final <T extends AbstractEntity> Data<T> retrieveXMLContent(String location, Class<?>... aClass) {
        if (location != null) {
            final InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(location);
            return this.xmlEncoder.convert(resourceAsStream, aClass);
        }
        return null;
    }

    /**
     * Injects the implementation class for {@link XMLEncoder}. This is normally done by the IOC container.
     * 
     * @param xmlEncoder
     *            implementation class for {@link XMLEncoder}.
     */
    @Inject
    protected final void setXmlEncoder(XMLEncoder xmlEncoder) {
        this.xmlEncoder = xmlEncoder;
    }

}