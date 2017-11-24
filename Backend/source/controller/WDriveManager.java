package controller;

import model.FileSystemDir;
import model.FileSystemFile;
import model.WFileSystem;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class WDriveManager extends WDriveHelper {

    private Boolean cutFlag;
    private List<String> cutboard;
    private FileSystemFile clipboard;

    private List<String> dirnames;
    private Stack<FileSystemDir> dirStack;


    public WDriveManager() {
        super();
        cutFlag = false;
    }

    private List<WDriveFile> startSession() throws Exception{
        fileSystem = account.getCloud();
        currentDir = (FileSystemDir) fileSystem.getFile("drive");
        virtualDirname = currentDir.getAbsolutePath();
        startStacks();
        return listFiles();
    }

    private void startStacks(){
        dirStack = new Stack<>();
        dirStack.push(fileSystem);
        dirnames = new ArrayList<>();
        dirnames.add("drive");
    }

    private void refreshFileSystem() throws Exception{
        fileSystem = fileSystemManager.load(account.getUsername());
        currentDir = refreshDir(dirnames);
    }

    public FileSystemDir refreshDir(List<String> dirnames) throws Exception{
        FileSystemDir dir = fileSystem;
        for(String dirname : dirnames)
            dir = (FileSystemDir) dir.getFile(dirname);
        return dir;
    }

    private FileSystemDir getCurrentDir() throws Exception{
        currentDir = refreshDir(dirnames);
        return currentDir;
    }

    public WDriveFile searchFile(String filename) throws Exception{
        FileSystemFile file = getCurrentDir().getFile(filename);
        checkNull(file, msgFileNotExists);
        return new WDriveFile(file);
    }

    public List<WDriveFile> createAccount(String username, String password, Long space) throws Exception{
        account = accountManager.create(username, password, space);
        return startSession();
    }

    public List<WDriveFile> loadAccount(String username, String password) throws Exception {
        account = accountManager.load(username, password);
        return startSession();
    }

    public Boolean fileExists(String filename) throws Exception{
        try { searchFile(filename); return true; }
        catch (Exception e){ return false;}
    }

    public WDriveFile createDir(String dirname) throws Exception{
        FileSystemFile file = fileSystem.create(getCurrentDir(), dirname);
        return new WDriveFile(file);
    }

    public WDriveFile createFile(String filename, String content) throws Exception{
        FileSystemFile file = fileSystem.create(getCurrentDir(), filename, content);
        return new WDriveFile(file);
    }

    public List<WDriveFile> accessDir(String dirname) throws Exception {
        refreshFileSystem();
        if (dirname.equals(".."))
            return accessParent();
        else
            return accessChild(dirname);
    }

    private List<WDriveFile> accessParent() throws Exception {
        dirnames.remove(dirnames.size()-1);
        currentDir = dirStack.pop();
        virtualDirname = new File(virtualDirname).getParent();
        return listFiles();
    }

    private List<WDriveFile> accessChild(String dirname) throws Exception {
        dirnames.add(dirname);
        dirStack.push(currentDir);
        currentDir = (FileSystemDir) getCurrentDir().getFile(dirname);
        virtualDirname = Paths.get(virtualDirname, dirname).toString();
        return listFiles();
    }

    public List<WDriveFile> listFiles() throws Exception {
        ArrayList<WDriveFile> files = new ArrayList<>();
        getCurrentDir().getFiles().forEach(file -> files.add(new WDriveFile(file)));
        return files;
    }

    public WDriveFile copyFile(String filename) throws Exception {
        clipboard = getCurrentDir().getFile(filename);
        return new WDriveFile(clipboard);
    }

    public WDriveFile pasteFile() throws Exception {
        FileSystemFile file = cutFlag ?
                fileSystem.move(clipboard, refreshDir(cutboard), currentDir):
                fileSystem.copy(clipboard, getCurrentDir());
        cutFlag = false;
        return new WDriveFile(file);
    }

    public WDriveFile cutFile(String filename) throws Exception {
        cutFlag = true;
        cutboard = new ArrayList<>(dirnames);
        return copyFile(filename);
    }

    public List<WDriveFile> deleteFile(String filename) throws Exception{
        FileSystemFile file = currentDir.getFile(filename);
        Boolean virtual = !account.getUsername().equals(extractUsername(file.getAbsolutePath()));
        fileSystem.delete(getCurrentDir(), filename, virtual);
        return listFiles();
    }

    public List<WDriveFile> shareFile(String filename, String username) throws Exception{
        if(!accountManager.exists(username))
            throw new Exception(msgAccountNotExists);
        FileSystemFile file = getCurrentDir().getFile(filename);
        WFileSystem target = fileSystemManager.load(username);
        fileSystem.share(file, target);
        return listFiles();
    }

    public String openFile(String filename) throws Exception{
        FileSystemFile file = getCurrentDir().getFile(filename);
        if(file == null)
            throw new Exception(msgFileNotExists);
        if(file instanceof FileSystemDir)
            throw new Exception(msgNotFile);
        return FileUtils.readFileToString(new File(file.getAbsolutePath()));
    }

}
