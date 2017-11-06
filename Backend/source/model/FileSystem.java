package model;

import java.io.File;
import org.apache.commons.io.FileUtils;

public class FileSystem extends FileSystemDir implements IMessage {

    public FileSystem(String pathname) throws Exception{
        super(pathname);
    }

    public FileSystemFile create(String dirname) throws Exception {
        File file = new File(dirname);
        FileSystemDir dir = parent(dirname);
        if(dir == null)
            throw new Exception(msgDirNotExists);
        FileSystemFile node = new FileSystemDir(dirname);
        if (!file.isDirectory() && !file.mkdirs())
            throw new Exception(msgDirNotCreated);
        return dir.add(node);
    }

    public FileSystemFile create(String filename, String content) throws Exception{
        File file = new File(filename);
        FileSystemDir dir = parent(filename);
        if(dir == null)
            throw new Exception(msgDirNotExists);
        FileUtils.writeStringToFile(file, content);
        FileSystemFile node = new FileSystemFile(filename);
        return dir.add(node);
    }

    public FileSystemDir parent(String filename) throws Exception{
        File file = new File(filename);
        File parent = file.getParentFile();
        return (FileSystemDir) search(parent.getPath());
    }

    public FileSystemFile search(String pathname){
        return search(pathname, (FileSystemFile) this);
    }

    public FileSystemFile search(String pathname, FileSystemFile node){
        if (node == null) return null;
        else if (node.getPathname().equals(pathname)) return node;
        else if(!(node instanceof FileSystemDir)) return null;
        return search(pathname, (FileSystemDir) node);
    }

    public FileSystemFile search(String pathname, FileSystemDir dir){
        for(FileSystemFile child : dir.getNodes()){
            FileSystemFile node = search(pathname, child);
            if(node != null) return node;
        }   return null;
    }

    private boolean isDir(String pathname){
        FileSystemFile node = search(pathname);
        return node instanceof FileSystemDir;
    }

}
