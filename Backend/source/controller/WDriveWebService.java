package controller;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService
public class WDriveWebService  extends CloudManager {

    @Resource
    private WebServiceContext context;

    private String message = new String("Hola, ");

    public String Hello() {
        return "Esto es un nuevo hola";
    }

    @WebMethod
    public String sayHello(String name) {
        return message + name + ".";
    }

}
