package api;

import controller.WDriveManager;
import controller.WDriveMessage;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class WDriveApp extends Application {

    private WDriveManager drive = new WDriveManager();

    @Override
    public Set<Class<?>> getClasses() {
        HashSet hashSet = new HashSet<Class<?>>();
        hashSet.add( WDriveManager.class );
        return hashSet;
    }

    public String getCurrentDirname() {
        return drive.getCurrentDirname();
    }

    public void setCurrentDirname(String currentDirname) {
        drive.setCurrentDirname(currentDirname);
    }

    private WDriveMessage onSuccessMessage(Object object){
        return new WDriveMessage(WDriveMessage.OK, object);
    }

    private WDriveMessage onErrorMessage(Exception exception){
        return new WDriveMessage(WDriveMessage.ERROR, exception.getMessage());
    }

    public WDriveMessage searchFile(String filename) {
        try{ return onSuccessMessage(drive.searchFile(filename)); }
        catch (Exception exception){ return onErrorMessage(exception); }
    }

    public  WDriveMessage createAccount(String username, String password, Long space) {
        try{ return onSuccessMessage(drive.createAccount(username, password, space)); }
        catch (Exception exception){ return onErrorMessage(exception); }
    }

    public WDriveMessage loadAccount(String username, String password) {
        try{ return onSuccessMessage(drive.loadAccount(username, password)); }
        catch (Exception exception){ return onErrorMessage(exception); }
    }

    public WDriveMessage fileExists(String filename) {
        try{ return onSuccessMessage(drive.fileExists(filename)); }
        catch (Exception exception){ return onErrorMessage(exception); }
    }

    public WDriveMessage createDir(String dirname) {
        try{ return onSuccessMessage(drive.createDir(dirname)); }
        catch (Exception exception){ return onErrorMessage(exception); }
    }

    public WDriveMessage createFile(String filename, String content) {
        try{ return onSuccessMessage(drive.createFile(filename, content)); }
        catch (Exception exception){ return onErrorMessage(exception); }
    }

    public WDriveMessage accessDir(String dirname) {
        try{ return onSuccessMessage(drive.accessDir(dirname)); }
        catch (Exception exception){ return onErrorMessage(exception); }
    }

    public WDriveMessage listFiles() {
        try{ return onSuccessMessage(drive.listFiles()); }
        catch (Exception exception){ return onErrorMessage(exception); }
    }

}