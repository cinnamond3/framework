package framework.extensions.utilities;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.EmptyInterceptor;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.ejb.HibernatePersistence;


/**
 * Credits to <a href="http://blog.krecan.net/2009/01/24/spring-managed-hibernate-interceptor-in-jpa/">Java crumbs</a>.
 * 
 * @author Frederick Yap
 */
@Named
@SuppressWarnings("deprecation")
class CustomHibernatePersistence extends HibernatePersistence {
    
    private final EmptyInterceptor interceptor;
    
    @Inject
    protected CustomHibernatePersistence(EmptyInterceptor interceptor) {
        this.interceptor = interceptor;
    }
    
    /*
     * (non-Javadoc)
     * @see
     * org.hibernate.ejb.HibernatePersistence#createContainerEntityManagerFactory(javax.persistence.spi.PersistenceUnitInfo
     * , java.util.Map)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo info, Map map) {
        final Ejb3Configuration cfg = new Ejb3Configuration();
        final Ejb3Configuration configured = cfg.configure(info, map);
        this.postprocessConfiguration(info, map, configured);
        return configured != null ? configured.buildEntityManagerFactory() : null;
    }
    
    @SuppressWarnings({ "rawtypes" })
    protected void postprocessConfiguration(PersistenceUnitInfo info, Map map, Ejb3Configuration configured) {
        if (this.interceptor != null) {
            if ((configured.getInterceptor() == null)
                    || EmptyInterceptor.class.equals(configured.getInterceptor().getClass())) {
                configured.setInterceptor(this.interceptor);
            } else {
                throw new IllegalStateException("Hibernate interceptor already set in persistence.xml ("
                        + configured.getInterceptor() + ")");
            }
        }
    }
}
