package model;

import java.util.*;

public class FileSystemDir extends FileSystemFile {

    protected List<FileSystemFile> nodes;

    public List<FileSystemFile> getNodes() {
        return nodes;
    }

    public FileSystemDir(String pathname) throws Exception {
        super(pathname);
        this.creationTime = new Date().getTime();
        this.pathname = pathname;
        this.nodes = new ArrayList<>();
    }

    public FileSystemFile add(FileSystemFile node){
        nodes.add(node);
        return node;
    }

    public FileSystemFile remove(FileSystemFile node){
        nodes.remove(node);
        return node;
    }

    public Boolean contains(FileSystemFile node){
        return nodes.contains(node);
    }

}
