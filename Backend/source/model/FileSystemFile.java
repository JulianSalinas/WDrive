package model;

import java.io.File;
import java.util.Date;
import org.apache.commons.io.FileUtils;

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

    public FileSystemFile update(){
        this.lastModifiedTime = getLastModifiedTime();
        this.size = getSize();
        return this;
    }

}
