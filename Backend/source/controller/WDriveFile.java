package controller;

import model.FileSystemFile;
import util.TimeConverter;

public class WDriveFile {

    private String name;
    private String filename;
    private String creationTime;
    private String lastModifiedTime;
    private long size;

    public WDriveFile(FileSystemFile file){
        name = file.getName();
        filename = file.getAbsolutePath();
        creationTime = TimeConverter.millisToString(file.getCreationTime());
        lastModifiedTime = TimeConverter.millisToString(file.getLastModifiedTime());
        size = file.getSize();
    }

}
