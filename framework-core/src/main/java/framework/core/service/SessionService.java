package framework.core.service;

import framework.core.entity.Session;

public interface SessionService extends Service<Session> {

    Session findSession(String userid, String sessionid);

}
