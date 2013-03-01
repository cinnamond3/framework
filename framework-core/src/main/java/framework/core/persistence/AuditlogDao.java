package framework.core.persistence;

import framework.core.entity.Auditlog;

public interface AuditlogDao extends Dao<Auditlog> {

    Auditlog findLastAuditlogByCurrentDetail(String group);

}
