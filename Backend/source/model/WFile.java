package model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class WFile extends WAttr{

    private WFile parent;

    public WFile(String pathname) throws Exception{
        this.pathname = pathname;
        this.setAttributes(readAttributes());
    }

    private BasicFileAttributes readAttributes() throws Exception{
        Path path = new File(pathname).toPath();
        return Files.readAttributes(path, BasicFileAttributes.class);
    }

    private void setAttributes(BasicFileAttributes attributes){
        this.size = attributes.size();
        this.creationTime = attributes.creationTime().toMillis();
        this.lastModifiedTime = attributes.lastModifiedTime().toMillis();
    }

}
