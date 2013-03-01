package framework.ui.datagenerator;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.Role;
import framework.core.service.RoleService;
import framework.core.utilities.DataGenerator;

@Named
public class RoleDataGenerator extends DataGenerator<Role> {

    @Inject
    protected RoleDataGenerator(RoleService service) {
        super(service);
    }

    /* (non-Javadoc)
     * @see framework.core.utilities.DataGenerator#getXML()
     */
    @Override
    protected String getXML() {
        return "DBVersion2.data";
    }
    
}
