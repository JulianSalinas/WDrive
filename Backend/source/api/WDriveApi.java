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

    @WebMethod
    public Object fileExists(
        @WebParam(name="filename") String filename){
        return serializer.toXML(wDrive.fileExists(filename));
    }

    @WebMethod
    public Object createFile(
            @WebParam(name="parentDirname") String parentDirname,
            @WebParam(name="filename") String filename,
            @WebParam(name="content") String content){
        return serializer.toXML(wDrive.createFile(parentDirname, filename, content));
    }

    @WebMethod
    public Object listFiles(
            @WebParam(name="dirname") String dirname){
        return serializer.toXML(wDrive.listFiles(dirname));
    }

    @WebMethod
    public Object fileProperties(
            @WebParam(name="filename") String filename){
        return serializer.toXML(wDrive.fileProperties(filename));
    }

}
