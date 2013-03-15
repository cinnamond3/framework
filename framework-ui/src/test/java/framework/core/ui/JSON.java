package framework.core.ui;

import org.json.JSONObject;

import framework.ui.request.ServiceRequest;
import framework.ui.request.SystemParameterRequest;

public class JSON {

    public static void main(String[] args) {
        JSONObject json = new JSONObject(new ServiceRequest<SystemParameterRequest>());
        System.out.println(json);
    }
    
}
