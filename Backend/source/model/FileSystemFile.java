package model;

import org.apache.commons.io.FileUtils;

import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

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
        filename = Files.copy(
                filename.toPath(),
                toDir.filename.toPath(),
                REPLACE_EXISTING).toFile();
        return this;
    }

    public FileSystemFile move(FileSystemFile toDir) throws Exception{
        filename = Files.move(
                filename.toPath(),
                toDir.filename.toPath(),
                REPLACE_EXISTING).toFile();
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
