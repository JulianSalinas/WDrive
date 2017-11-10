package model;

import java.io.IOException;
import java.util.*;

public class FileSystemDir extends FileSystemFile {

    protected List<FileSystemFile> files;

    public FileSystemDir(String pathname) throws Exception{
        super(pathname);
        this.files = new ArrayList<>();
        if(!filename.exists() && !filename.mkdir())
            throw new IOException();
    }

    public List<FileSystemFile> getFiles() {
        return files;
    }

    @Override
    public Long getSize() {
        size = 0L;
        for(FileSystemFile file : files)
            size += file.getSize();
        return size;
    }

    public FileSystemFile add(FileSystemFile file){
        files.add(file);
        return file;
    }

    public FileSystemFile remove(FileSystemFile file){
        files.remove(file);
        return file;
    }

    public FileSystemFile search(String pathname){
        return search(pathname, (FileSystemFile) this);
    }

    public FileSystemFile search(String pathname, FileSystemFile file){
        if(file.getPath().equals(pathname))
            return file;
        else if(!(file instanceof FileSystemDir))
            return null;
        return search(pathname, (FileSystemDir) file);
    }

    public FileSystemFile search(String pathname, FileSystemDir dir){
        for(FileSystemFile child : dir.getFiles()){
            FileSystemFile node = search(pathname, child);
            if(node != null) return node;
        }   return null;
    }

}
