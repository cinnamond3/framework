package framework.core.service.impl;

import javax.inject.Named;

import framework.core.entity.User;
import framework.core.persistence.UserDao;
import framework.core.service.UserService;

/**
 * Performs business operations for {@link User} entity/
 * 
 * @author Frederick Yap
 */
@Named
public class UserServiceImpl extends AbstractService<User, UserDao> implements UserService {

    private static final long serialVersionUID = 5506093159372637005L;

}
