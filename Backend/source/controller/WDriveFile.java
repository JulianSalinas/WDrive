package controller;

import model.FileSystemDir;
import model.FileSystemFile;
import util.TimeConverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WDriveFile {

    private String name;
    private String filename;
    private String creationTime;
    private String lastModifiedTime;
    private long size;
    private boolean isDir;

    public WDriveFile(FileSystemFile file){
        name = file.getName();
        filename = mapPath(file.getAbsolutePath());
        creationTime = TimeConverter.millisToString(file.getCreationTime());
        lastModifiedTime = TimeConverter.millisToString(file.getLastModifiedTime());
        size = file.getSize();
        isDir = file instanceof FileSystemDir;
    }

    public String mapPath(String filename){
        Pattern pattern = Pattern.compile(".cloud.(\\w+).*");
        Matcher matcher = pattern.matcher(filename);
        matcher.find();
        String dirname = matcher.group(0);
        return dirname.replace("\\cloud", "");
    }

}
