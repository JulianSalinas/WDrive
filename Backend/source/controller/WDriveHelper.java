package controller;

import model.FileSystemDir;
import model.ICloud;
import model.WAccount;
import model.WFileSystem;
import util.XmlSerializer;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class WDriveHelper implements ICloud {

    protected WAccount account;
    protected WFileSystem fileSystem;

    protected String virtualDirname;
    protected FileSystemDir currentDir;

    protected XmlSerializer serializer;
    protected AccountManager accountManager;
    protected FileSystemManager fileSystemManager;

    public WDriveHelper() {
        try { initComponents(); }
        catch (Exception e){ exitOnException(e); }
    }

    private void initComponents() throws Exception{
        serializer = new XmlSerializer();
        accountManager = new AccountManager();
        fileSystemManager = new FileSystemManager();
    }

    public String getCurrentDirname() {
        return virtualDirname;
    }

    public Long getTotalSpace() throws Exception{
        return fileSystem.getTotalSpace();
    }

    public Long getAvailableSpace() throws Exception{
        return fileSystem.getAvailableSpace();
    }

    protected void exitOnException(Exception e){
        e.printStackTrace();
        System.exit(1);
    }

    protected void checkNull(Object obj, String message) throws Exception{
        if(obj == null)
            throw new Exception(message);
    }

    protected String extractUsername(String filename) throws Exception{
        Pattern pattern = Pattern.compile(".+cloud.(\\w+)");
        Matcher matcher = pattern.matcher(filename);
        if (matcher.find())
            return new File(matcher.group(1)).getName();
        else throw new Exception(msgDirNotExists);
    }

    protected WFileSystem searchFileSystem(String filename) throws Exception{
        String username = extractUsername(filename);
        return fileSystemManager.load(username);
    }

}
