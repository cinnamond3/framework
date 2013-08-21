package framework.core.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import framework.core.entity.SystemParameter;
import framework.core.enums.ParameterCode;
import framework.core.persistence.SystemParameterDao;

/**
 * Data access implementation for persisting {@link SystemParameter} entity.
 * 
 * @author Frederick Yap
 */
@Named
public class SystemParameterDaoImpl extends AbstractDao<SystemParameter> implements SystemParameterDao {

    private static final long serialVersionUID = -5030590659029576233L;

    /*
     * (non-Javadoc)
     * @see framework.core.persistence.SystemParameterDao#findAllActiveSystemParam()
     */
    @Override
    public List<SystemParameter> findAllActiveSystemParam() {
        return this.find("findAllActiveSystemParam");
    }

    /*
     * (non-Javadoc)
     * @see
     * framework.core.persistence.SystemParameterDao#findSystemParametersByCode(framework.core.constants.ParameterCode)
     */
    @Override
    public List<SystemParameter> findSystemParametersByCode(ParameterCode code) {
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("code", code);
        return this.find("findSystemParametersByCode", parameters);
    }

}
