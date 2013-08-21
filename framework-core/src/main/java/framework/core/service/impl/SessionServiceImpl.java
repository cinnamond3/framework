package framework.core.service.impl;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.Session;
import framework.core.enums.ParameterCode;
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
    public Session findSession(String userid, String sessionid) {
        final List<Session> sessions = this.sessionDao.findBySessionId(sessionid);
        if (sessions.size() == 1) {
            final Session session = sessions.get(0);
            final String paramValue = this.systemParameterService.findByCode(ParameterCode.SESSION_TIMEOUT).getValue();
            final Integer add = Integer.valueOf(this.getCryptography().decrypt(paramValue));
            if ((userid.equals(session.getUser().getId())) && (this.getDateUtils().isBefore(session.getExpiry()))) {
                session.setExpiry(this.getDateUtils().addSecondsUnixTime(add));
                session.setSessionid(UUID.randomUUID().toString());
                return this.sessionDao.saveOrUpdate(session);
            }
        }
        return null;
    }

    @Inject
    protected void setSystemParameterService(SystemParameterService systemParameterService) {
        this.systemParameterService = systemParameterService;
    }
}
