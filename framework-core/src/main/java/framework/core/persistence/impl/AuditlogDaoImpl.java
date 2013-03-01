package framework.core.persistence.impl;

import javax.inject.Named;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import framework.core.entity.Auditlog;
import framework.core.persistence.AuditlogDao;

@Named
public class AuditlogDaoImpl extends AbstractDao<Auditlog> implements AuditlogDao {

    private static final long serialVersionUID = -7807736241131100379L;

    @Override
    public Auditlog findLastAuditlogByCurrentDetail(String detail) {
        try {
            final Query query = this.createNamedQuery("findLastAuditlogByCurrentDetail");
            query.setParameter("detail", detail + "%");
            query.setMaxResults(1);
            query.setFlushMode(FlushModeType.COMMIT);
            return (Auditlog) query.getSingleResult();
        } catch (final NoResultException e) {
            return null;
        }
    }
}
