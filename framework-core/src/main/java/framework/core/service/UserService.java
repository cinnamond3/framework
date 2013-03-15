package framework.core.service;

import framework.core.entity.Session;
import framework.core.entity.User;

/**
 * This interface represents business operations for {@link User} entity.
 * 
 * @author Frederick Yap
 */
public interface UserService extends Service<User> {

    Session authenticate(String username, String password);
}
