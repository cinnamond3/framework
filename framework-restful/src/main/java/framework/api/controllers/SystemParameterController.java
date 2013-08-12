package framework.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import framework.api.request.SystemParameterRequest;
import framework.api.response.ServiceResponse;
import framework.api.response.SystemParameterResponse;
import framework.core.entity.SystemParameter;
import framework.core.service.SystemParameterService;
import framework.core.utilities.Cryptography;

@Named
@Path("/systemParameter")
public class SystemParameterController extends AbstractController<SystemParameterRequest, SystemParameterResponse> {

    private static final long serialVersionUID = 200605594031531073L;
    private SystemParameterService systemParameterService;
    private Cryptography cryptography;
    
    @GET
    @RolesAllowed(value={"Administrator"})
    public ServiceResponse<SystemParameterResponse> processRequest() {
        final List<SystemParameterResponse> list = new ArrayList<SystemParameterResponse>();
        List<SystemParameter> systemParameters =  this.systemParameterService.findAllActiveSystemParam();
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
        ServiceResponse<SystemParameterResponse> serviceResponse = new ServiceResponse<>();
        serviceResponse.setResults(list);
        return serviceResponse;
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
    
}
