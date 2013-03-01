package framework.core.persistence.impl;

import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

import framework.core.constants.ParameterCode;
import framework.core.entity.SystemParameter;
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
    @SuppressWarnings("unchecked")
    @Override
    public List<SystemParameter> findAllActiveSystemParam() {
        final Query query = this.createNamedQuery("findAllActiveSystemParam");
        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     * @see
     * framework.core.persistence.SystemParameterDao#findSystemParametersByCode(framework.core.constants.ParameterCode)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SystemParameter> findSystemParametersByCode(ParameterCode code) {
        final Query query = this.createNamedQuery("findSystemParametersByCode");
        query.setParameter("code", code);
        List<SystemParameter> systemParameters = query.getResultList();
        for (SystemParameter systemParameter : systemParameters) {
            this.detach(systemParameter);
        }
        return systemParameters;
    }
    
}
