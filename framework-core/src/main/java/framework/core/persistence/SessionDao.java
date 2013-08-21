package framework.core.persistence;

import java.util.List;

import framework.core.entity.Session;

public interface SessionDao extends Dao<Session> {
    
    List<Session> findBySessionId(String sessionId);
    
}
