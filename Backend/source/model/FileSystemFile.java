package model;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;

public class FileSystemFile extends FileSystemNode{

    public FileSystemFile(String pathname) {
        this.filename = new File(pathname);
        this.creationTime = new Date().getTime();
        this.lastModifiedTime = creationTime;
        this.size = 0L;
    }

    public FileSystemFile(String pathname, String content) throws Exception{
        this(pathname);
        FileUtils.writeStringToFile(filename, content);
    }

    public Long getSize() {
        return filename.length();
    }

    public FileSystemFile delete() throws Exception{
        filename.delete();
        return this;
    }

    public FileSystemFile copy(FileSystemDir dir) throws Exception{
        String newFilename = Paths.get(dir.getPath(), getName()).toString();
        String content = FileUtils.readFileToString(filename);
        FileSystemFile newfile =  new FileSystemFile(newFilename, content);
        return dir.add(newfile);
    }

    public FileSystemFile update(){
        this.lastModifiedTime = getLastModifiedTime();
        this.size = getSize();
        return this;
    }

}
