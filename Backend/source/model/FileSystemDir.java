package model;

import java.util.*;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

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

    @Override
    public FileSystemFile delete() throws Exception{
        FileUtils.deleteDirectory(filename);
        return this;
    }

    public FileSystemFile add(FileSystemFile file){
        if(search(file.getPath()) == null)
            files.add(file.update());
        return file;
    }

    public FileSystemFile remove(FileSystemFile file) throws Exception{
        if(search(file.getPath()) != null)
            files.remove(file.delete());
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
