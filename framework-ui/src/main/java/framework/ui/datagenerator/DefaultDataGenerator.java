package framework.ui.datagenerator;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.entity.SystemParameter;
import framework.core.service.SystemParameterService;
import framework.core.utilities.DataGenerator;

@Named
public class DefaultDataGenerator extends DataGenerator<SystemParameter> {
    
    @Inject
    protected DefaultDataGenerator(SystemParameterService service) {
        super(service);
    }

    @Override
    protected String getXML() {
        return "DBVersion1.data";
    }

}
