package framework.core.persistence.impl;

import javax.inject.Named;

import framework.core.entity.User;
import framework.core.persistence.UserDao;

/**
 * Data access implementation for persisting {@link User} entity.
 * 
 * @author Frederick Yap
 */
@Named
public class UserDaoImpl extends AbstractDao<User> implements
		UserDao {

	private static final long serialVersionUID = -508553230014446994L;

}
