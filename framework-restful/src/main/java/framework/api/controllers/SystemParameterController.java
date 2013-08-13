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
import framework.core.constants.ApplicationStatus;
import framework.core.entity.SystemParameter;
import framework.core.service.SystemParameterService;
import framework.core.utilities.Cryptography;

@Named
@Path("/systemParameter")
public class SystemParameterController extends AbstractController<SystemParameterRequest, SystemParameterResponse> {

    private static final long serialVersionUID = 200605594031531073L;
    private Cryptography cryptography;
    private SystemParameterService systemParameterService;

    @GET
    @RolesAllowed(value = { "Administrator" })
    public ServiceResponse<SystemParameterResponse> processRequest() {
        final List<SystemParameterResponse> list = new ArrayList<SystemParameterResponse>();
        final List<SystemParameter> systemParameters = this.systemParameterService.findAllActiveSystemParam();
        for (final SystemParameter systemParameter : systemParameters) {
            final SystemParameterResponse systemParameterDTO = new SystemParameterResponse();
            systemParameterDTO.setCode(systemParameter.getCode().name());
            systemParameterDTO.setDescription(systemParameter.getDescription());
            systemParameterDTO.setMaximum(String.valueOf(systemParameter.getMaximum()));
            systemParameterDTO.setMinimum(String.valueOf(systemParameter.getMinimum()));
            systemParameterDTO.setType(systemParameter.getType().name());
            systemParameterDTO.setReadonly(String.valueOf(systemParameter.isReadonly()));
            systemParameterDTO.setValue(this.cryptography.decrypt(systemParameter.getValue()));
            list.add(systemParameterDTO);
        }
        return ServiceResponse.results(list).status(ApplicationStatus.SUCCESS).build();
    }

    @Inject
    protected void setCryptography(Cryptography cryptography) {
        this.cryptography = cryptography;
    }

    /**
     * @param systemParameterService
     *            the systemParameterService to set
     */
    @Inject
    protected void setSystemParameterService(SystemParameterService systemParameterService) {
        this.systemParameterService = systemParameterService;
    }

}
