package framework.core.service.impl;

import javax.inject.Named;

import framework.core.entity.Role;
import framework.core.persistence.RoleDao;
import framework.core.service.RoleService;

@Named
public class RoleServiceImpl extends AbstractService<Role, RoleDao> implements RoleService {

    private static final long serialVersionUID = -675261136460381643L;

}
