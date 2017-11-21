package controller;

import model.ICloud;
import model.WAccount;
import util.XmlMessage;
import util.XmlSerializer;

public class WDriveManager implements ICloud {

    protected XmlSerializer serializer;
    protected AccountManager accountManager;
    protected FileSystemManager fileSystemManager;

    public WDriveManager() {
        try {
            this.serializer = new XmlSerializer();
            this.accountManager = new AccountManager();
            this.fileSystemManager = new FileSystemManager();
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Object createAccount(String username, String password, Long space){
        try{
            WAccount account = accountManager.create(username, password, space);
            return new XmlMessage(XmlMessage.OK, account.getCloud());
        }
        catch (Exception e){
            return new XmlMessage(XmlMessage.ERROR, e.getMessage());
        }
    }

    public Object loadAccount(String username, String password){
        try{
            WAccount account = accountManager.load(username, password);
            return new XmlMessage(XmlMessage.OK, account.getCloud());
        }
        catch (Exception e){
            return new XmlMessage(XmlMessage.ERROR, e.getMessage());
        }
    }

}
