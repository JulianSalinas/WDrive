package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class WDriveManager extends WDriveHelper {

    private Boolean cutFlag;
    private FileSystemDir cutboard;
    private FileSystemFile clipboard;
    private Stack<FileSystemDir> dirStack;


    public WDriveManager() {
        super();
        cutFlag = false;
    }

    private List<WDriveFile> startSession() throws Exception{
        fileSystem = account.getCloud();
        currentDir = (FileSystemDir) fileSystem.getFile("drive");
        virtualDirname = currentDir.getAbsolutePath();
        dirStack = new Stack<>();
        dirStack.push(fileSystem);
        return listFiles();
    }

    public WDriveFile searchFile(String filename) throws Exception{
        FileSystemFile file = currentDir.getFile(filename);
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
        FileSystemFile file = fileSystem.create(currentDir, dirname);
        return new WDriveFile(file);
    }

    public WDriveFile createFile(String filename, String content) throws Exception{
        FileSystemFile file = fileSystem.create(currentDir, filename, content);
        return new WDriveFile(file);
    }

    public List<WDriveFile> accessDir(String dirname) throws Exception {
        if (dirname.equals("..")) currentDir = dirStack.pop();
        else {
            dirStack.push(currentDir);
            currentDir = (FileSystemDir) currentDir.getFile(dirname);
        }
        return listFiles();
    }

    public List<WDriveFile> listFiles() throws Exception {
        ArrayList<WDriveFile> files = new ArrayList<>();
        currentDir.getFiles().forEach(file -> files.add(new WDriveFile(file)));
        return files;
    }

    public WDriveFile copyFile(String filename) throws Exception {
        clipboard = currentDir.getFile(filename);
        return new WDriveFile(clipboard);
    }

    public WDriveFile pasteFile() throws Exception {
        FileSystemFile file = cutFlag ?
                fileSystem.move(clipboard, cutboard, currentDir):
                fileSystem.copy(clipboard, currentDir);
        cutFlag = false;
        return new WDriveFile(file);
    }

    public WDriveFile cutFile(String filename) throws Exception {
        cutFlag = true;
        cutboard = currentDir;
        return copyFile(filename);
    }

    public List<WDriveFile> deleteFile(String filename) throws Exception{
        fileSystem.delete(currentDir, filename);
        return listFiles();
    }

    public List<WDriveFile> shareFile(String filename, String username) throws Exception{
        FileSystemFile file = currentDir.getFile(filename);
        WFileSystem target = fileSystemManager.load(username);
        fileSystem.share(file, target);
        return listFiles();
    }

}
