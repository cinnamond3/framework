package framework.ui.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

import framework.core.entity.SystemParameter;
import framework.core.service.SystemParameterService;
import framework.ui.request.SystemParameterRequest;
import framework.ui.response.SystemParameterResponse;

@Named
@Path("/systemParameters")
public class SystemParametersController extends BaseController<SystemParameterRequest, SystemParameterResponse> {

    private static final long serialVersionUID = 200605594031531073L;
    private SystemParameterService systemParameterService;

    @Override
    public List<SystemParameterResponse> processResult(SystemParameterRequest t) {
        final List<SystemParameterResponse> list = new ArrayList<SystemParameterResponse>();
        for (final SystemParameter systemParameter : this.systemParameterService.findAllActiveSystemParam()) {
            final SystemParameterResponse systemParameterDTO = new SystemParameterResponse();
            systemParameterDTO.setCode(systemParameter.getCode().name());
            systemParameterDTO.setDescription(systemParameter.getDescription());
            systemParameterDTO.setMaximum(String.valueOf(systemParameter.getMaximum()));
            systemParameterDTO.setMinimum(String.valueOf(systemParameter.getMinimum()));
            systemParameterDTO.setType(systemParameter.getType().name());
            systemParameterDTO.setValue(systemParameter.getValue());
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

}
