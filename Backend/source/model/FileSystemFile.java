package model;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Paths;

public class FileSystemFile extends FileSystemNode{

    public FileSystemFile(String pathname) {
        super(pathname);
    }

    public FileSystemFile(String pathname, String content) throws Exception{
        this(pathname);
        FileUtils.writeStringToFile(filename, content);
    }

    public Long getSize() {
        return filename.length();
    }

    public FileSystemFile copy(FileSystemFile toDir) throws Exception{
        File file = Paths.get(toDir.getAbsolutePath(), getName()).toFile();
        String content = FileUtils.readFileToString(filename);
        FileUtils.copyFileToDirectory(filename, toDir.filename,true);
        return new FileSystemFile(file.getAbsolutePath(), content);
    }

    public FileSystemFile move(FileSystemFile toDir) throws Exception{
        FileUtils.moveFileToDirectory(filename, toDir.filename, true);
        filename = Paths.get(toDir.getAbsolutePath(), getName()).toFile();
        return this;
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
