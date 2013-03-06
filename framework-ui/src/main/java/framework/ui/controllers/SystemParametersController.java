package framework.ui.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import framework.core.entity.SystemParameter;
import framework.core.service.SystemParameterService;
import framework.ui.databeans.ResponseHeader;
import framework.ui.databeans.ServiceResponse;
import framework.ui.databeans.SystemParameterDTO;

@Named
@Path("/systemParameters")
public class SystemParametersController {

    private SystemParameterService systemParameterService;

    @GET
    @Path("retrieveAllActive")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceResponse<SystemParameterDTO> retrieveAllActive() {
        final ServiceResponse<SystemParameterDTO> response = new ServiceResponse<>();
        final ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setStatusCode(0);
        responseHeader.setStatusMessage("SUCCESS");
        final List<SystemParameterDTO> list = new ArrayList<SystemParameterDTO>();
        for (final SystemParameter systemParameter : this.systemParameterService.findAllActiveSystemParam()) {
            final SystemParameterDTO systemParameterDTO = new SystemParameterDTO();
            systemParameterDTO.setCode(systemParameter.getCode().name());
            systemParameterDTO.setDescription(systemParameter.getDescription());
            systemParameterDTO.setId(systemParameterDTO.getId());
            systemParameterDTO.setMaximum(String.valueOf(systemParameter.getMaximum()));
            systemParameterDTO.setMinimum(String.valueOf(systemParameter.getMinimum()));
            systemParameterDTO.setType(systemParameter.getType().name());
            systemParameterDTO.setValue(systemParameter.getValue());
            list.add(systemParameterDTO);
        }
        response.setResponseHeader(responseHeader);
        response.setResults(list.toArray(new SystemParameterDTO[list.size()]));
        return response;
    }

    @GET
    @Path("retrieveById")
    @Produces(MediaType.APPLICATION_JSON)
    public ServiceResponse<SystemParameterDTO> retrieveById(@QueryParam(value = "id") String id) {
        final ServiceResponse<SystemParameterDTO> response = new ServiceResponse<>();
        final ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setStatusCode(0);
        responseHeader.setStatusMessage("SUCCESS");
        final List<SystemParameterDTO> list = new ArrayList<SystemParameterDTO>();
        for (final SystemParameter systemParameter : Arrays.asList(this.systemParameterService.findById(id))) {
            final SystemParameterDTO systemParameterDTO = new SystemParameterDTO();
            systemParameterDTO.setCode(systemParameter.getCode().name());
            systemParameterDTO.setDescription(systemParameter.getDescription());
            systemParameterDTO.setId(systemParameterDTO.getId());
            systemParameterDTO.setMaximum(String.valueOf(systemParameter.getMaximum()));
            systemParameterDTO.setMinimum(String.valueOf(systemParameter.getMinimum()));
            systemParameterDTO.setType(systemParameter.getType().name());
            systemParameterDTO.setValue(systemParameter.getValue());
            list.add(systemParameterDTO);
        }
        response.setResponseHeader(responseHeader);
        response.setResults(list.toArray(new SystemParameterDTO[list.size()]));
        return response;
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
