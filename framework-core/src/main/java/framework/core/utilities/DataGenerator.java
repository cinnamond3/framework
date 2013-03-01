package framework.core.utilities;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.AbstractEntity;
import framework.core.entity.Data;
import framework.core.service.Service;

/**
 * Base class for data generator classes.
 * 
 * @author Frederick Yap
 */
@Named
public abstract class DataGenerator<T extends AbstractEntity> {

    private final Class<T> persistentClass;
    private final Service<T> service;
    private XMLEncoder xmlEncoder;

    /**
     * Default constructor.
     * 
     * @param service
     *            Service implementation to be used for business operation.
     */
    @SuppressWarnings("unchecked")
    protected DataGenerator(Service<T> service) {
        final Type[] type = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        this.service = service;
        this.persistentClass = (Class<T>) type[0];
    }

    /**
     * Returns the new database version. Normally, the database version is supplied by <em>DBVersion{}.data</em> file.
     * However, for some cases, it is necessary to override this method especially if no <em>DBVersion{}.data</em> is
     * provided.
     * 
     * @return the new database version.
     */
    protected Integer getDBVersion() {
        return this.retrieveXMLContent().getVersion();
    }

    /**
     * Returns an instance of Service implementation used for business operations.
     * 
     * @return instance of Service implementation.
     */
    protected final Service<T> getService() {
        return this.service;
    }

    /**
     * Override this method to return the XML file containing the data to be appended.
     * 
     * @return the XML file.
     */
    protected String getXML() {
        return null;
    }

    /**
     * Override this method to perform additional tasks.
     */
    protected void performDataOperation() {

    }

    /**
     * Initialize this class.
     */
    protected final Data<T> retrieveXMLContent() {
        final String xml = this.getXML();
        if (xml != null) {
            final InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(xml);
            return this.xmlEncoder.convert(resourceAsStream, this.persistentClass);
        }
        return null;
    }

    /**
     * Saves or update entries to the database.
     */
    protected final void saveOrUpdate() {
        final Data<T> data = this.retrieveXMLContent();
        if (data != null) {
            for (final T t : data.getRecords()) {
                this.service.saveOrUpdate(t);
            }
        }
        this.performDataOperation();
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