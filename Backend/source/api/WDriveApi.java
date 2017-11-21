package api;

import controller.WDriveManager;
import util.XmlSerializer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class WDriveApi {

    private XmlSerializer serializer = new XmlSerializer();
    private WDriveManager wDrive = new WDriveManager();

    @WebMethod
    public String sayHello(String name) {
        return "Hola " + name + "!";
    }

    @WebMethod
    public String createAccount(
            @WebParam(name="username") String username,
            @WebParam(name="password") String password,
            @WebParam(name="space") long space){
        return serializer.toXML(wDrive.createAccount(username, password, space));
    }

    @WebMethod
    public String loadAccount(
            @WebParam(name="username") String username,
            @WebParam(name="password") String password){
        return serializer.toXML(wDrive.loadAccount(username, password));
    }

}
