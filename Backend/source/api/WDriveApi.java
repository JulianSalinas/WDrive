package api;

import controller.WDriveManager;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class WDriveApi extends WDriveManager {

    private String message = new String("Hola, ");

    @WebMethod
    public String Hello() {
        return "Esto es un nuevo hola";
    }

    @WebMethod
    public String sayHello(String name) {
        return message + name + ".";
    }

}
