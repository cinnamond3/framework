package framework.core.service;

import framework.core.entity.Session;

public interface SessionService extends Service<Session> {

    boolean isValidSession(String userid, String sessionid);

}
