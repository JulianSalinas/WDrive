package model;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Paths;
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

    public void setFiles(List<FileSystemFile> files) {
        this.files = files;
    }

    public FileSystemFile add(FileSystemFile file){
        if(!files.contains(file))
            files.add(file);
        return file;
    }

    public FileSystemFile remove(FileSystemFile file) throws Exception{
        if(files.contains(file))
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
        FileUtils.copyDirectoryToDirectory(filename, toDir.filename);
        FileSystemDir dir = new FileSystemDir(Paths.get(toDir.getAbsolutePath(), getName()).toString());
        dir.setFiles(getFiles());
        return dir;
    }

    @Override
    public FileSystemFile move(FileSystemFile toDir) throws Exception{
        FileUtils.moveDirectoryToDirectory(filename, toDir.filename, true);
        FileSystemDir dir = new FileSystemDir(Paths.get(toDir.getAbsolutePath(), getName()).toString());
        dir.setFiles(getFiles());
        return dir;
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
