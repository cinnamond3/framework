package framework.ui.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

import framework.core.constants.ParameterCode;
import framework.core.entity.SystemParameter;
import framework.core.service.SystemParameterService;
import framework.core.utilities.Cryptography;
import framework.ui.request.RequestHeader;
import framework.ui.request.SystemParameterRequest;
import framework.ui.response.SystemParameterResponse;

@Named
@Path("/systemParameter")
public class SystemParameterController extends AbstractController<SystemParameterRequest, SystemParameterResponse> {

    private static final long serialVersionUID = 200605594031531073L;
    private SystemParameterService systemParameterService;
    private Cryptography cryptography;
    
    @Override
    public List<SystemParameterResponse> processRequest(SystemParameterRequest t) {
        final List<SystemParameterResponse> list = new ArrayList<SystemParameterResponse>();
        List<SystemParameter> systemParameters = new ArrayList<SystemParameter>();
        if (t==null) {
            systemParameters.addAll(this.systemParameterService.findAllActiveSystemParam());
        } else {
            systemParameters.add(this.systemParameterService.findByCode(ParameterCode.valueOf(t.getCode())));
        }
        for (final SystemParameter systemParameter : systemParameters) {
            final SystemParameterResponse systemParameterDTO = new SystemParameterResponse();
            systemParameterDTO.setCode(systemParameter.getCode().name());
            systemParameterDTO.setDescription(systemParameter.getDescription());
            systemParameterDTO.setMaximum(String.valueOf(systemParameter.getMaximum()));
            systemParameterDTO.setMinimum(String.valueOf(systemParameter.getMinimum()));
            systemParameterDTO.setType(systemParameter.getType().name());
            systemParameterDTO.setReadonly(String.valueOf(systemParameter.isReadonly()));
            systemParameterDTO.setValue(cryptography.decrypt(systemParameter.getValue()));
            list.add(systemParameterDTO);
        }
        return list;
    }

    /**
     * @param systemParameterService
     *            the systemParameterService to set
     */
    @Inject
    protected void setSystemParameterService(SystemParameterService systemParameterService) {
        this.systemParameterService = systemParameterService;
    }

    @Inject
    protected void setCryptography(Cryptography cryptography) {
        this.cryptography = cryptography;
    }

    @Override
    protected boolean isAccessible(RequestHeader requestHeader) {
        return true;
    }
    
}
