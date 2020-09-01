package edu.escuelaing.arep.components;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Response {
    private String resource;
    private HashMap<String,String> queryStrings;

    public Response(String resource) throws UnsupportedEncodingException {

        queryStrings = new HashMap();

        if(resource.contains("?")){
            this.resource = resource.split("\\?")[0];
            String[] params = resource.split("\\?")[1].split("&");
            for(int i = 0; i<params.length;i++){
                System.out.println(params[i]);
                queryStrings.put(params[i].split("=")[0],URLDecoder.decode(params[i].split("=")[1], StandardCharsets.UTF_8.name()));
            }
        } else {
            this.resource = resource;
        }

    }

    public String getResource(){
        return resource;
    }

    public String getQueryString(String param){
        return queryStrings.get(param);
    }

    public void setResource(String resource){
        this.resource = resource;
    }

}
