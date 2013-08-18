package framework.core.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.ParameterCode;
import framework.core.entity.Session;
import framework.core.entity.SystemParameter;
import framework.core.entity.User;
import framework.core.exceptions.CredentialExpiredException;
import framework.core.exceptions.InvalidUserException;
import framework.core.exceptions.UserProfileExpiredException;
import framework.core.persistence.UserDao;
import framework.core.service.SessionService;
import framework.core.service.SystemParameterService;
import framework.core.service.UserService;
import framework.core.utilities.Cryptography;
import framework.core.utilities.DateUtils;

/**
 * Performs business operations for {@link User} entity/
 * 
 * @author Frederick Yap
 */
@Named
public class UserServiceImpl extends AbstractService<User, UserDao> implements UserService {

    private static final long serialVersionUID = 5506093159372637005L;
    private Cryptography cryptography;
    private DateUtils dateUtils;
    private SessionService sessionService;
    private SystemParameterService systemParameterService;

    @Override
    public Session authenticate(String username, String password) {
        final List<User> users = this.getPersistence().findUsersByUsername(username);
        if (users.size() == 1) {
            return this.prepareSession(users.get(0), password);
        }
        throw new InvalidUserException(String.format("Username [%s] does not exist.", username));
    }

    protected Session prepareSession(User user, String password) {
        final Session session = new Session();
        final SystemParameter systemParameter = this.systemParameterService.findByCode(ParameterCode.SESSION_TIMEOUT);
        if (this.dateUtils.getCurrentUnixTime() > user.getProfileexpiration()) {
            throw new UserProfileExpiredException(String.format("User [%s] has already expired.", user.getName()));
        }
        if (this.dateUtils.getCurrentUnixTime() > user.getPasswordexpiration()) {
            throw new CredentialExpiredException(String.format("Credentials for user [%s] has already expired.", user.getName()));
        }
        if (!password.equals(this.cryptography.decrypt(user.getPassword()))) {
            throw new InvalidUserException(String.format("Password for [%s] does not match with the supplied password.", user.getName()));
        }
        session.setUser(user);
        session.setStart(this.dateUtils.getCurrentUnixTime());
        session.setExpiry(this.dateUtils.addSecondsUnixTime(Integer.valueOf(this.cryptography.decrypt(systemParameter
                .getValue()))));
        return this.sessionService.saveOrUpdate(session);
    }

    @Inject
    protected void setCryptography(Cryptography cryptography) {
        this.cryptography = cryptography;
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
