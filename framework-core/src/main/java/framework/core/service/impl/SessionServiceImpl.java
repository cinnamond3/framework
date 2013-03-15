package framework.core.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.ParameterCode;
import framework.core.entity.Session;
import framework.core.entity.SystemParameter;
import framework.core.persistence.SessionDao;
import framework.core.service.SessionService;
import framework.core.service.SystemParameterService;
import framework.core.utilities.DateUtils;

@Named
public class SessionServiceImpl extends AbstractService<Session, SessionDao> implements SessionService {

    private static final long serialVersionUID = -6724981340291285304L;
    private DateUtils dateUtils;
    private SystemParameterService systemParameterService;

    @Override
    public boolean isValidSession(String userid, String sessionid) {
        final Session session = this.getPersistence().findById(sessionid);
        final SystemParameter systemParameter = this.systemParameterService.findByCode(ParameterCode.SESSION_TIMEOUT);
        boolean isValidSession = false;
        if (session != null) {
            if ((userid.equals(session.getUser().getId())) && (this.dateUtils.getCurrentUnixTime() < session.getEnd())) {
                session.setEnd(this.dateUtils.addSecondsUnixTime(Integer.valueOf(systemParameter.getValue())));
                this.getPersistence().saveOrUpdate(session);
                isValidSession = true;
            }

        }
        return isValidSession;
    }

    @Inject
    protected void setDateUtils(DateUtils dateUtils) {
        this.dateUtils = dateUtils;
    }

    @Inject
    protected void setSystemParameterService(SystemParameterService systemParameterService) {
        this.systemParameterService = systemParameterService;
    }
}
