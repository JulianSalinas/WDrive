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

    protected XmlSerializer serializer;
    protected AccountManager accountManager;
    protected FileSystemManager fileSystemManager;

    public WDriveManager() {
        try { initComponents(); }
        catch (Exception e){ exitOnException(e); }
    }

    private void initComponents() throws Exception{
        this.serializer = new XmlSerializer();
        this.accountManager = new AccountManager();
        this.fileSystemManager = new FileSystemManager();
    }

    private void exitOnException(Exception e){
        e.printStackTrace();
        System.exit(1);
    }

    private void throwExceptionOnNull(Object obj, String message) throws Exception{
        if(obj == null)
            throw new Exception(message);
    }

    private String extractUsername(String pathname) throws Exception{
        Pattern pattern = Pattern.compile(".+cloud.(\\w+)");
        Matcher matcher = pattern.matcher(pathname);
        if (matcher.find())
            return new File(matcher.group(1)).getName();
        else throw new Exception(msgDirNotExists);
    }

    private WFileSystem searchFileSystem(String filename) throws Exception{
        String username = extractUsername(filename);
        return fileSystemManager.load(username);
    }

    public WDriveFile searchFile(String filename) throws Exception{
        WFileSystem fs = searchFileSystem(filename);
        FileSystemFile file = fs.search(filename);
        if(file == null) return null;
        return new WDriveFile(fs.search(filename));
    }

    public List<WDriveFile> createAccount(String username, String password, Long space) throws Exception{
        WAccount account = accountManager.create(username, password, space);
        return listFiles(account.getCloud().getAbsolutePath());
    }

    public List<WDriveFile>  loadAccount(String username, String password) throws Exception {
        WAccount account = accountManager.load(username, password);
        return listFiles(account.getCloud().getAbsolutePath());
    }

    public Boolean fileExists(String filename) throws Exception{
        WDriveFile file = searchFile(filename);
        if(file == null) return false;
        else return true;
    }

    public WDriveFile createDir(String parentDirname, String dirname) throws Exception{
        FileSystem fs = searchFileSystem(dirname);
        FileSystemDir parent = (FileSystemDir) fs.search(parentDirname);
        throwExceptionOnNull(parent, msgDirNotExists);
        dirname = Paths.get(parentDirname, dirname).toString();
        return new WDriveFile(fs.create(dirname));
    }

    public WDriveFile createFile(String parentDirname, String filename, String content) throws Exception{
        FileSystem fs = searchFileSystem(filename);
        FileSystemDir parent = (FileSystemDir) fs.search(parentDirname);
        throwExceptionOnNull(parent, msgDirNotExists);
        filename = Paths.get(parentDirname, filename).toString();
        return new WDriveFile(fs.create(filename, content));
    }

    public List<WDriveFile> listFiles(String dirname) throws Exception {
        WFileSystem fs = searchFileSystem(dirname);
        FileSystemDir dir = (FileSystemDir) fs.search(dirname);
        throwExceptionOnNull(dir, msgDirNotExists);
        ArrayList<WDriveFile> files = new ArrayList<>();
        dir.getFiles().forEach(file -> files.add(new WDriveFile(file)));
        return files;
    }

}
