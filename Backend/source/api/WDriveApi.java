package api;

import util.XmlSerializer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class WDriveApi {

    private WDriveApp app = new WDriveApp();
    private XmlSerializer serializer = new XmlSerializer();

    private String response(Object object){
        return serializer.toXML(object);
    }

    @WebMethod
    public String getCurrentDirname() {
        return app.getCurrentDirname();
    }

    @WebMethod
    public String searchFile(
            @WebParam(name="filename") String filename){
        return response(app.searchFile(filename));
    }

    @WebMethod
    public String createAccount(
            @WebParam(name="username") String username,
            @WebParam(name="password") String password,
            @WebParam(name="space") long space){
        return response(app.createAccount(username, password, space));
    }

    @WebMethod
    public String loadAccount(
            @WebParam(name="username") String username,
            @WebParam(name="password") String password){
        return response(app.loadAccount(username, password));
    }

    @WebMethod
    public Object fileExists(
        @WebParam(name="filename") String filename){
        return response(app.fileExists(filename));
    }

    @WebMethod
    public Object createDir(
            @WebParam(name="dirname") String dirname){
        return response(app.createDir(dirname));
    }

    @WebMethod
    public Object createFile(
            @WebParam(name="parentDirname") String parentDirname,
            @WebParam(name="filename") String filename,
            @WebParam(name="content") String content){
        return response(app.createFile(parentDirname, filename, content));
    }

    @WebMethod
    public Object listFiles(
            @WebParam(name="dirname") String dirname){
        return response(app.listFiles(dirname));
    }

}
