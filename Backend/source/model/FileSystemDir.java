package model;

import java.util.*;

public class FileSystemDir extends FileSystemNode {

    private List<FileSystemNode> nodes;

    public List<FileSystemNode> getNodes() {
        return nodes;
    }

    public FileSystemDir(String pathname) throws Exception {
        this.pathname = pathname;
        nodes = new ArrayList<>();
    }

    public FileSystemNode create(String pathname){
        for(FileSystemNode node: nodes)
            if(node.getPathname().equals(pathname))
                return node;
        return null;
    }

    public FileSystemNode search(String pathname){
        for(FileSystemNode node: nodes)
            if(node.getPathname().equals(pathname))
                return node;
        return null;
    }

    public FileSystemNode add(FileSystemNode node){
        nodes.add(node);
        return node;
    }

    public void remove(FileSystemNode node){
        nodes.remove(node);
    }

    public Boolean contains(FileSystemNode node){
        return nodes.contains(node);
    }
}
