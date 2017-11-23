package model;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSystemDir extends FileSystemFile {

    protected List<FileSystemFile> files;

    public FileSystemDir(String pathname) throws Exception{
        this(pathname, false);
    }

    public FileSystemDir(String pathname, boolean isVirtual) throws Exception{
        super(pathname);
        if(!isVirtual && !filename.exists() && !filename.mkdir())
            throw new IOException();
        this.files = new ArrayList<>();
    }

    public List<FileSystemFile> getFiles() {
        return files;
    }

    public FileSystemFile add(FileSystemFile file){
        files.add(file);
        return file;
    }

    public FileSystemFile remove(FileSystemFile file) throws Exception{
        files.remove(file);
        return file;
    }

    public FileSystemFile create(String filename, String content) throws Exception{
        FileSystemFile child = new FileSystemFile(filename, content);
        return add(child);
    }

    public FileSystemDir create(String dirname) throws Exception {
        FileSystemDir child = new FileSystemDir(dirname);
        return (FileSystemDir) add(child);
    }

    @Override
    public Long getSize() {
        size = 0L;
        for(FileSystemFile file : files)
            size += file.getSize();
        return size;
    }

    @Override
    public FileSystemFile copy(FileSystemFile toDir) throws Exception{
        super.copy(toDir);
        for (FileSystemFile file : files)
            file.copy(this);
        return this;
    }

    @Override
    public FileSystemFile move(FileSystemFile toDir) throws Exception{
        super.move(toDir);
        for (FileSystemFile file : files)
            file.move(this);
        return this;
    }

    @Override
    public FileSystemFile delete() throws Exception{
        FileUtils.deleteDirectory(filename);
        return this;
    }

    @Override
    public FileSystemFile update(){
        for(FileSystemFile file: files)
            file.update();
        this.lastModifiedTime = getLastModifiedTime();
        this.size = getSize();
        return this;
    }

    public FileSystemFile getFile(String filename) throws Exception{
        for (FileSystemFile file : getFiles()){
            if (file.getName().equals(filename))
                return file;
        }
        return null;
    }

}
