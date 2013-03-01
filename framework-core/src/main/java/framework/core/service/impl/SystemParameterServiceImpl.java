package framework.core.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.ParameterCode;
import framework.core.entity.SystemParameter;
import framework.core.persistence.SystemParameterDao;
import framework.core.service.SystemParameterService;
import framework.core.utilities.Cryptography;

/**
 * Performs business operations for {@link SystemParameter} entity.
 * 
 * @author Frederick Yap
 */
@Named
public class SystemParameterServiceImpl extends AbstractService<SystemParameter, SystemParameterDao> implements
        SystemParameterService {

    private static final long serialVersionUID = 1541672630059263485L;

    private Cryptography cryptography;
    
    
    /**
     * Sets an instance of {@link Cryptography} implementation to be used by this class.
     * @param cryptography the {@link Cryptography} implementation injected by the IOC container.
     */
    @Inject
    protected void setCryptography(Cryptography cryptography) {
        this.cryptography = cryptography;
    }

    /*
     * (non-Javadoc)
     * @see framework.core.service.SystemParameterService#findAllActiveSystemParam()
     */
    @Override
    public List<SystemParameter> findAllActiveSystemParam() {
        List<SystemParameter> systemParameters =  this.getPersistence().findAllActiveSystemParam();
        for (SystemParameter systemParameter : systemParameters) {
            systemParameter.setValue(cryptography.decrypt(systemParameter.getValue()));
        }
        return systemParameters;
    }

    /*
     * (non-Javadoc)
     * @see
     * framework.core.service.SystemParameterService#findSystemParametersByCode(framework.core.domain.SystemParameter
     * .ParameterCode)
     */
    @Override
    public SystemParameter findByCode(ParameterCode code) {
        final List<SystemParameter> systemParameters = this.getPersistence().findSystemParametersByCode(code);
        if (systemParameters.size() > 0) {
            SystemParameter systemParameter = systemParameters.get(0);
            systemParameter.setValue(cryptography.decrypt(systemParameter.getValue()));
            return systemParameter;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see framework.core.service.impl.AbstractService#saveOrUpdate(T[])
     */
    @Override
    public void saveOrUpdate(SystemParameter systemParameter) {
        systemParameter.setValue(cryptography.encrypt(systemParameter.getValue()));
        this.getPersistence().saveOrUpdate(systemParameter);
    }

}
