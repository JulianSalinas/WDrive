package model;

import org.apache.commons.io.FileUtils;
import util.IMessage;
import util.IOFunction;

import java.io.File;

public class FileSystem implements IMessage{

    protected FileSystemNode root;

    public FileSystem(String pathname) throws Exception{
        this.root = new FileSystemDir(pathname);
    }

    public FileSystemNode search(String pathname){
        return search(pathname, root);
    }

    public FileSystemNode search(String pathname, FileSystemNode node){
        if (node == null) return null;
        else if (node.getPathname().equals(pathname)) return node;
        else if(node instanceof FileSystemFile) return null;
        return search(pathname, (FileSystemDir) node);
    }

    public FileSystemNode search(String pathname, FileSystemDir dir){
        for(FileSystemNode child : dir.getNodes()){
            FileSystemNode node = search(pathname, child);
            if(node != null) return node;
        }   return null;
    }

    public Object create(String dirname) throws Exception{
        File file = new File(dirname);
        IOFunction.mkdirs(dirname);
        return file;
    }

    public Object create(String filename, Object content) throws Exception{
        File file = new File(filename);
        FileUtils.writeStringToFile(file, (String) content);
        return file;
    }

    private boolean isFile(String pathname){
        FileSystemNode node = search(pathname);
        return node instanceof FileSystemFile;
    }

    private boolean isDir(String pathname){
        FileSystemNode node = search(pathname);
        return node instanceof FileSystemDir;
    }

}
