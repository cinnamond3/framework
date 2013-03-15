package framework.core.persistence.impl;

import javax.inject.Named;

import framework.core.entity.Session;
import framework.core.persistence.SessionDao;

@Named
public class SessionDaoImpl extends AbstractDao<Session> implements SessionDao {

    private static final long serialVersionUID = -1385640701108483561L;

}
