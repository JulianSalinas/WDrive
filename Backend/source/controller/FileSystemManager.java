package controller;

import model.*;
import util.XmlSerializer;

import java.io.File;
import java.nio.file.Paths;

public class FileSystemManager implements ICloud {

    XmlSerializer serializer;

    public FileSystemManager() throws Exception {
        serializer = new XmlSerializer();
        File file = new File(cloudDirname);
        if (!file.isDirectory() && !file.mkdirs())
            throw new Exception(msgDirNotCreated);
    }

    public CloudFileSystem create(String username, Long space) throws Exception{
        String root = Paths.get(cloudDirname, username).toString();
        return new CloudFileSystem(root, space);
    }

    public CloudFileSystem load(String username) throws Exception{
        String pathname = Paths.get(cloudDirname, username, indexFilename).toString();
        return (CloudFileSystem) serializer.load(pathname);
    }

}
