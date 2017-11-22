package controller;

import model.*;
import util.XmlSerializer;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WDriveManager implements ICloud {

    private String currentDirname;
    protected XmlSerializer serializer;
    protected AccountManager accountManager;
    protected FileSystemManager fileSystemManager;

    public WDriveManager() {
        try { initComponents(); }
        catch (Exception e){ exitOnException(e); }
    }

    private void initComponents() throws Exception{
        this.currentDirname = null;
        this.serializer = new XmlSerializer();
        this.accountManager = new AccountManager();
        this.fileSystemManager = new FileSystemManager();
    }

    public String getCurrentDirname() {
        return currentDirname;
    }

    public void setCurrentDirname(String currentDirname) {
        this.currentDirname = currentDirname;
    }

    private void exitOnException(Exception e){
        e.printStackTrace();
        System.exit(1);
    }

    private void throwExceptionOnNull(Object obj, String message) throws Exception{
        if(obj == null)
            throw new Exception(message);
    }

    private String extractUsername(String filename) throws Exception{
        Pattern pattern = Pattern.compile(".+cloud.(\\w+)");
        Matcher matcher = pattern.matcher(filename);
        if (matcher.find())
            return new File(matcher.group(1)).getName();
        else throw new Exception(msgDirNotExists);
    }

    private WFileSystem searchFileSystem(String filename) throws Exception{
        String username = extractUsername(filename);
        return fileSystemManager.load(username);
    }

    public WDriveFile searchFile(String filename) throws Exception{
        WFileSystem fs = searchFileSystem(currentDirname);
        filename = Paths.get(currentDirname, filename).toString();
        FileSystemFile file = fs.search(filename);
        if(file == null) return null;
        return new WDriveFile(file);
    }

    public List<WDriveFile> createAccount(String username, String password, Long space) throws Exception{
        WAccount account = accountManager.create(username, password, space);
        currentDirname = account.getCloud().getDriveDirname();
        return listFiles();
    }

    public List<WDriveFile> loadAccount(String username, String password) throws Exception {
        WAccount account = accountManager.load(username, password);
        currentDirname = account.getCloud().getDriveDirname();
        return listFiles();
    }

    public Boolean fileExists(String filename) throws Exception{
        WDriveFile file = searchFile(filename);
        if(file == null) return false;
        else return true;
    }

    public WDriveFile createDir(String dirname) throws Exception{
        WFileSystem fs = searchFileSystem(currentDirname);
        dirname = Paths.get(currentDirname, dirname).toString();
        return new WDriveFile(fs.create(dirname));
    }

    public WDriveFile createFile(String filename, String content) throws Exception{
        WFileSystem fs = searchFileSystem(currentDirname);
        filename = Paths.get(currentDirname, filename).toString();
        return new WDriveFile(fs.create(filename, content));
    }

    public List<WDriveFile> accessDir(String dirname) throws Exception {
        if (dirname.equals(".."))
            currentDirname = new File(currentDirname).getParent();
        else
            currentDirname = Paths.get(currentDirname, dirname).toString();
        return listFiles();
    }

    public List<WDriveFile> listFiles() throws Exception {
        WFileSystem fs = searchFileSystem(currentDirname);
        FileSystemDir dir = (FileSystemDir) fs.search(currentDirname);
        throwExceptionOnNull(dir, msgDirNotExists);
        ArrayList<WDriveFile> files = new ArrayList<>();
        dir.getFiles().forEach(file -> files.add(new WDriveFile(file)));
        return files;
    }

}
