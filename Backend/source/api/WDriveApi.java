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
    public String getTotalSpace() {
        return response(app.getTotalSpace());
    }

    @WebMethod
    public String getAvailableSpace() {
        return response(app.getAvailableSpace());
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
    public String fileExists(
        @WebParam(name="filename") String filename){
        return response(app.fileExists(filename));
    }

    @WebMethod
    public String createDir(
            @WebParam(name="dirname") String dirname){
        return response(app.createDir(dirname));
    }

    @WebMethod
    public String createFile(
            @WebParam(name="filename") String filename,
            @WebParam(name="content") String content){
        return response(app.createFile(filename, content));
    }

    @WebMethod
    public String accessDir(
            @WebParam(name="dirname") String dirname){
        return response(app.accessDir(dirname));
    }

    @WebMethod
    public String listFiles(){
        return response(app.listFiles());
    }

    @WebMethod
    public String copyFile(
            @WebParam(name="filename") String filename) {
        return response(app.copyFile(filename));
    }

    @WebMethod
    public String pasteFile() {
        return response(app.pasteFile());
    }

    @WebMethod
    public String cutFile(
            @WebParam(name="filename") String filename) {
        return response(app.cutFile(filename));
    }

    @WebMethod
    public String deleteFile(
            @WebParam(name="filename") String filename) {
        return response(app.deleteFile(filename));
    }

    @WebMethod
    public String shareFile(
            @WebParam(name="filename") String filename,
            @WebParam(name="username") String username) {
        return response(app.shareFile(filename, username));
    }

    @WebMethod
    public String openFile(
            @WebParam(name="filename") String filename) {
        return response(app.openFile(filename));
    }

}
