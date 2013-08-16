package framework.core.utilities;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.Client;
import framework.core.entity.Localization;
import framework.core.entity.Role;
import framework.core.entity.SystemParameter;
import framework.core.entity.User;
import framework.core.entity.Usergroup;
import framework.core.service.ClientService;
import framework.core.service.SystemParameterService;
import framework.core.service.UserService;

@Named
public class DefaultDataGenerator extends DataGenerator {

    private Cryptography cryptography;
    private SystemParameterService systemParameterService;
    private UserService userService;
    private ClientService clientService;

    @Override
    protected Integer getDBVersion() {
        return 1;
    }

    @Override
    protected void performDataOperation() {
        final List<SystemParameter> systemParameters = this.retrieveXMLContent("DefaultSystemParameters.data", SystemParameter.class, Localization.class);
        final User user = (User) this.retrieveXMLContent("DefaultUser.data", User.class, Usergroup.class, Role.class, Client.class);      
        final Client client = this.clientService.saveOrUpdate((Client)this.retrieveXMLContent("DefaultClient.data", Client.class));
        
        for (final SystemParameter systemParameter : systemParameters) {
            systemParameter.setValue(this.cryptography.encrypt(systemParameter.getValue()));
        }
        this.systemParameterService.saveOrUpdate(systemParameters);

        user.setPassword(this.cryptography.encrypt(user.getPassword()));
        user.setClient(client);
        user.getUsergroup().getClients().add(client);
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

    @Inject
    protected void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    
}
