package framework.ui.response;

import java.io.Serializable;

public class SessionResponse implements Serializable {

    private static final long serialVersionUID = -7717653586881038941L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private String sessionId;

    private String usergroupId;

    private String userId;

    public String getSessionId() {
        return this.sessionId;
    }

    public String getUsergroupId() {
        return this.usergroupId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setUsergroupId(String usergroupId) {
        this.usergroupId = usergroupId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
