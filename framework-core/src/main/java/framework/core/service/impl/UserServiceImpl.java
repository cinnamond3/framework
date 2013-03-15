package framework.core.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.ParameterCode;
import framework.core.constants.ServiceError;
import framework.core.entity.Session;
import framework.core.entity.SystemParameter;
import framework.core.entity.User;
import framework.core.persistence.UserDao;
import framework.core.service.SessionService;
import framework.core.service.SystemParameterService;
import framework.core.service.UserService;
import framework.core.service.exceptions.AuthenticationException;
import framework.core.utilities.DateUtils;

/**
 * Performs business operations for {@link User} entity/
 * 
 * @author Frederick Yap
 */
@Named
public class UserServiceImpl extends AbstractService<User, UserDao> implements UserService {

    private static final long serialVersionUID = 5506093159372637005L;
    private DateUtils dateUtils;
    private SessionService sessionService;
    private SystemParameterService systemParameterService;

    @Override
    public Session authenticate(String username, String password) {
        final List<User> users = this.getPersistence().findUsersByUsername(username);
        if (users.size() == 1) {
            return this.prepareSession(users.get(0), password);
        }
        throw new AuthenticationException(ServiceError.INVALID_USER);
    }

    protected Session prepareSession(User user, String password) {
        final Session session = new Session();
        final SystemParameter systemParameter = this.systemParameterService.findByCode(ParameterCode.SESSION_TIMEOUT);
        if (this.dateUtils.getCurrentUnixTime() > user.getProfileexpiration()) {
            throw new AuthenticationException(ServiceError.EXPIRED_PROFILE);
        }
        if (this.dateUtils.getCurrentUnixTime() > user.getPasswordexpiration()) {
            throw new AuthenticationException(ServiceError.EXPIRED_CREDENTIALS);
        }
        if (!password.equals(user.getPassword())) {
            throw new AuthenticationException(ServiceError.INVALID_USER);
        }
        session.setUser(user);
        session.setStart(this.dateUtils.getCurrentUnixTime());
        session.setEnd(this.dateUtils.addSecondsUnixTime(Integer.valueOf(systemParameter.getValue())));
        this.sessionService.saveOrUpdate(session);
        return session;
    }

    @Inject
    protected void setDateUtils(DateUtils dateUtils) {
        this.dateUtils = dateUtils;
    }

    @Inject
    protected void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Inject
    protected void setSystemParameterService(SystemParameterService systemParameterService) {
        this.systemParameterService = systemParameterService;
    }
}
