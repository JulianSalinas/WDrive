package controller;

import model.FileSystemFile;
import util.TimeConverter;

public class WDriveFile {

    protected String name;
    protected String filename;
    protected String creationTime;
    protected String lastModifiedTime;
    protected long size;

    public WDriveFile(FileSystemFile file){
        name = file.getName();
        filename = file.getAbsolutePath();
        creationTime = TimeConverter.millisToString(file.getCreationTime());
        lastModifiedTime = TimeConverter.millisToString(file.getLastModifiedTime());
        size = file.getSize();
    }

}
