package framework.core.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Named;

import framework.core.entity.Auditlog;
import framework.core.persistence.AuditlogDao;
import framework.core.service.AuditlogService;

@Named
public class AuditlogServiceImpl extends AbstractService<Auditlog, AuditlogDao> implements AuditlogService {

    private static final long serialVersionUID = 8371424672724804463L;

    @Override
    public Auditlog findLastAuditlogByCurrentDetail(String detail) {
        final Pattern pattern = Pattern
                .compile("[a-zA-Z]*\\[id=\"[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}\"");
        final Matcher matcher = pattern.matcher(detail);
        if (matcher.find()) {
            return getPersistence().findLastAuditlogByCurrentDetail(matcher.group());
        }
        return null;
    }

}
