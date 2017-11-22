package controller;

import model.FileSystemFile;
import util.TimeConverter;

public class WDriveFile {

    private String name;
    private String filename;
    private String creationTime;
    private String lastModifiedTime;
    private long size;

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getLastModifiedTime() {
        return lastModifiedTime;
    }

    public long getSize() {
        return size;
    }

    public WDriveFile(FileSystemFile file){
        name = file.getName();
        filename = file.getAbsolutePath();
        creationTime = TimeConverter.millisToString(file.getCreationTime());
        lastModifiedTime = TimeConverter.millisToString(file.getLastModifiedTime());
        size = file.getSize();
    }

}
