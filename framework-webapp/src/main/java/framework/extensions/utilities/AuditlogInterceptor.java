package framework.extensions.utilities;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.context.ApplicationContext;

import framework.core.entity.AbstractEntity;
import framework.core.entity.Auditlog;
import framework.core.service.AuditlogService;
import framework.ui.databeans.AuditlogDTO;
import framework.ui.exceptions.AuditloggingException;
import framework.ui.utilities.BeanMapper;

@Named
public class AuditlogInterceptor extends EmptyInterceptor {

    private static final long serialVersionUID = 4291608653469256753L;

    private ApplicationContext applicationContext;

    /*
     * (non-Javadoc)
     * @see org.hibernate.EmptyInterceptor#onDelete(java.lang.Object, java.io.Serializable, java.lang.Object[],
     * java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public void onDelete(Object object, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (!(object instanceof Auditlog)) {
            final AuditlogDTO auditlogDTO = new AuditlogDTO();
            final AbstractEntity entity = (AbstractEntity) object;
            final String detail = this.generateAuditlogDetails(entity);
            auditlogDTO.setType("DELETE");
            auditlogDTO.setLogdate(Calendar.getInstance().getTime());
            auditlogDTO.setDetail(detail);
            final Auditlog previousLog = this.getAuditlogService().findLastAuditlogByCurrentDetail(detail);
            auditlogDTO.setPrevious(previousLog.getDetail());
            this.getAuditlogService().saveOrUpdate(getBeanMapper().convert(auditlogDTO, Auditlog.class));
        }
    }
    
    
    /*
     * (non-Javadoc)
     * @see org.hibernate.EmptyInterceptor#onFlushDirty(java.lang.Object, java.io.Serializable, java.lang.Object[],
     * java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public boolean onFlushDirty(Object object, Serializable id, Object[] currentState, Object[] previousState,
            String[] propertyNames, Type[] types) {
        if (!(object instanceof Auditlog)) {
            final AuditlogDTO auditlogDTO = new AuditlogDTO();
            final AbstractEntity entity = (AbstractEntity) object;
            final String detail = this.generateAuditlogDetails(entity);
            final Auditlog previousLog = this.getAuditlogService().findLastAuditlogByCurrentDetail(detail);
            if (previousLog != null) {
                auditlogDTO.setType("UPDATE"); 
                auditlogDTO.setLogdate(Calendar.getInstance().getTime());
                auditlogDTO.setDetail(detail);
                auditlogDTO.setPrevious(previousLog.getDetail());
                this.getAuditlogService().saveOrUpdate(getBeanMapper().convert(auditlogDTO, Auditlog.class));
            }
        }
        return true;
    }
    /*
     * (non-Javadoc)
     * @see org.hibernate.EmptyInterceptor#onSave(java.lang.Object, java.io.Serializable, java.lang.Object[],
     * java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public boolean onSave(Object object, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (!(object instanceof Auditlog)) {
            final AuditlogDTO auditlogDTO = new AuditlogDTO();
            final AbstractEntity entity = (AbstractEntity) object;
            auditlogDTO.setType("INSERT");
            auditlogDTO.setLogdate(Calendar.getInstance().getTime());
            auditlogDTO.setDetail(this.generateAuditlogDetails(entity));
            this.getAuditlogService().saveOrUpdate(this.getBeanMapper().convert(auditlogDTO, Auditlog.class));
        }
        return true;
    }

    private String generateAuditlogDetails(AbstractEntity entity) {
        final StringBuilder detail = new StringBuilder();
        final Table table = entity.getClass().getAnnotation(Table.class);
        try {
            if (table != null) {
                detail.append(table.name());
                detail.append("[");
                detail.append("id=");
                detail.append("\"");
                detail.append(entity.getId());
                detail.append("\", ");
                detail.append(this.getAuditlogFieldDetails(entity));
                detail.delete(detail.lastIndexOf(","), detail.length());
                detail.append("]");
            }
            return detail.toString();
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new AuditloggingException("Unable to generate auditlog detail.", e);
        }
    }

    private StringBuilder getAuditlogFieldDetails(AbstractEntity entity) throws IllegalAccessException {
        final Field[] fields = entity.getClass().getDeclaredFields();
        final StringBuilder fieldDetails = new StringBuilder();
        for (final Field field : fields) {
            field.setAccessible(true);
            final Column column = field.getAnnotation(Column.class);
            if (column != null) {
                if ("".equals(column.name())) {
                    fieldDetails.append(field.getName());
                } else {
                    fieldDetails.append(column.name());
                }
                fieldDetails.append("=\"");
                fieldDetails.append(field.get(entity));
                fieldDetails.append("\", ");
            }
            final JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
            if (joinColumn != null) {
                if ("".equals(joinColumn.name())) {
                    fieldDetails.append(field.getName());
                } else {
                    fieldDetails.append(joinColumn.name());
                }
                fieldDetails.append("=\"");
                fieldDetails.append(field.get(entity));
                fieldDetails.append("\", ");
            }
        }
        return fieldDetails;
    }

    private AuditlogService getAuditlogService() {
        return this.applicationContext.getBean(AuditlogService.class);
    }

    private BeanMapper getBeanMapper() {
        return this.applicationContext.getBean(BeanMapper.class);
    }

    /**
     * @param applicationContextAware
     *            the applicationContextAware to set
     */
    @Inject
    protected void setApplicationContextAware(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
