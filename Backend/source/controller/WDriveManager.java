package controller;

import model.*;
import util.XmlSerializer;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private String extractUsername(String pathname) throws Exception{
        Pattern pattern = Pattern.compile(".+cloud.(\\w+)");
        Matcher matcher = pattern.matcher(pathname);
        if (matcher.find())
            return new File(matcher.group(1)).getName();
        else throw new Exception(msgDirNotExists);
    }

    private FileSystemFile searchFile(String filename) throws Exception{
        String username = extractUsername(filename);
        WFileSystem fs = fileSystemManager.load(username);
        return fs.search(filename);
    }

    public Object createAccount(String username, String password, Long space){
        try{
            WAccount account = accountManager.create(username, password, space);
            return listFiles(account.getCloud().getAbsolutePath());
        }
        catch (Exception e){
            return new WDriveMessage(WDriveMessage.ERROR, e.getMessage());
        }
    }

    public Object loadAccount(String username, String password) {
        try {
            WAccount account = accountManager.load(username, password);
            return listFiles(account.getCloud().getAbsolutePath());
        } catch (Exception e) {
            return new WDriveMessage(WDriveMessage.ERROR, e.getMessage());
        }
    }

    public Object fileExists(String filename){
        try{
            Boolean response = true;
            FileSystemFile file = searchFile(filename);
            if(file == null) response = false;
            return new WDriveMessage(WDriveMessage.OK, response);
        }
        catch (Exception e){
            return new WDriveMessage(WDriveMessage.ERROR, e.getMessage());
        }
    }

    public Object createFile(String parentDirname, String filename, String content){
        try{
            String username = extractUsername(filename);
            FileSystem fs = fileSystemManager.load(username);
            FileSystemDir parent = (FileSystemDir) fs.search(parentDirname);
            if(parent == null)
                throw new Exception(msgDirNotExists);
            return new WDriveMessage(WDriveMessage.OK, fs.create(filename, content));
        }
        catch (Exception e){
            return new WDriveMessage(WDriveMessage.ERROR, e.getMessage());
        }
    }

    public Object listFiles(String dirname){
        try {
            ArrayList<WDriveFile> filesProperties = new ArrayList<>();
            FileSystemDir dir = (FileSystemDir) searchFile(dirname);
            if(dir == null)
                throw new Exception(msgDirNotExists);
            for(FileSystemFile file : dir.getFiles())
                filesProperties.add(new WDriveFile(file));
            return new WDriveMessage(WDriveMessage.OK, filesProperties.toArray());
        }
        catch(Exception e){
            return new WDriveMessage(WDriveMessage.ERROR, e.getMessage());
        }
    }

    public Object fileProperties(String filename){
        try {
            FileSystemFile file = searchFile(filename);
            if(file == null)
                throw new Exception(msgFileNotExists);
            return new WDriveMessage(WDriveMessage.OK, new WDriveFile(file));
        }
        catch(Exception e){
            return new WDriveMessage(WDriveMessage.ERROR, e.getMessage());
        }
    }

}
