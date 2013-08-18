package framework.core.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.ParameterCode;
import framework.core.entity.Session;
import framework.core.entity.SystemParameter;
import framework.core.persistence.SessionDao;
import framework.core.service.SessionService;
import framework.core.service.SystemParameterService;

@Named
public class SessionServiceImpl extends AbstractService<Session> implements SessionService {

    private static final long serialVersionUID = -6724981340291285304L;
    private final SessionDao sessionDao;

    private SystemParameterService systemParameterService;

    @Inject
    protected SessionServiceImpl(SessionDao sessionDao) {
        super(sessionDao);
        this.sessionDao = sessionDao;
    }

    @Override
    public boolean isValidSession(String userid, String sessionid) {
        boolean isValidSession = false;
        if ((userid != null) || (sessionid != null)) {
            final Session session = this.sessionDao.findById(sessionid);
            final SystemParameter systemParameter = this.systemParameterService
                    .findByCode(ParameterCode.SESSION_TIMEOUT);
            if (session != null) {
                if ((userid.equals(session.getUser().getId()))
                        && (this.getDateUtils().getCurrentUnixTime() < session.getExpiry())) {
                    session.setExpiry(this.getDateUtils().addSecondsUnixTime(Integer.valueOf(systemParameter.getValue())));
                    this.sessionDao.saveOrUpdate(session);
                    isValidSession = true;
                }

            }
        }
        return isValidSession;
    }

    @Inject
    protected void setSystemParameterService(SystemParameterService systemParameterService) {
        this.systemParameterService = systemParameterService;
    }
}
