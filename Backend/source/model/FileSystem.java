package model;

import java.io.File;
import org.apache.commons.io.FileUtils;

public class FileSystem implements IFileSystem {

    protected FileSystemNode root;

    public FileSystem(String pathname) throws Exception{
        this.root = new FileSystemDir(pathname);
    }

    @Override
    public FileSystemNode create(String dirname) throws Exception {
        File file = new File(dirname);
        if (!file.isDirectory() && !file.mkdirs())
            throw new Exception(msgDirNotCreated);
        File parent = file.getParentFile();
        FileSystemDir dir = (FileSystemDir) search(parent.getPath());
        FileSystemNode node = new FileSystemDir(dirname);
        return dir.add(node);
    }

    @Override
    public FileSystemNode create(String filename, String content) throws Exception{
        File file = new File(filename);
        FileUtils.writeStringToFile(file, content);
        File parent = file.getParentFile();
        FileSystemDir dir = (FileSystemDir) search(parent.getPath());
        FileSystemNode node = new FileSystemDir(filename);
        return dir.add(node);
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
//
//    private boolean isFile(String pathname){
//        FileSystemNode node = search(pathname);
//        return node instanceof FileSystemFile;
//    }
//
//    private boolean isDir(String pathname){
//        FileSystemNode node = search(pathname);
//        return node instanceof FileSystemDir;
//    }

}
