package framework.core.utilities;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.Client;
import framework.core.entity.Data;
import framework.core.entity.Role;
import framework.core.entity.SystemParameter;
import framework.core.entity.User;
import framework.core.entity.Usergroup;
import framework.core.service.SystemParameterService;
import framework.core.service.UserService;

@Named
public class DefaultDataGenerator extends DataGenerator {

    private Cryptography cryptography;
    private SystemParameterService systemParameterService;
    private UserService userService;

    @Override
    protected Integer getDBVersion() {
        return 1;
    }

    @Override
    protected void performDataOperation() {
        final Data<SystemParameter> systemParameterData = this.retrieveXMLContent("DBVersion1.data", SystemParameter.class);
        final List<SystemParameter> systemParameters = systemParameterData.getRecords();
        final User user = (User) this.retrieveXMLContent("DBVersion2.data", User.class, Usergroup.class, Role.class, Client.class).getRecords().get(0);

        for (final SystemParameter systemParameter : systemParameters) {
            systemParameter.setValue(this.cryptography.encrypt(systemParameter.getValue()));
        }
        this.systemParameterService.saveOrUpdate(systemParameters);

        user.setPassword(this.cryptography.encrypt(user.getPassword()));
        this.userService.saveOrUpdate(user);
    }

    @Inject
    protected void setCryptography(Cryptography cryptography) {
        this.cryptography = cryptography;
    }

    @Inject
    protected void setSystemParameterService(SystemParameterService systemParameterService) {
        this.systemParameterService = systemParameterService;
    }

    @Inject
    protected void setUserService(UserService userService) {
        this.userService = userService;
    }

}
