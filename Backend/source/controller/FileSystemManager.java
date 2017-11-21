package controller;

import model.ICloud;
import model.WFileSystem;
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

    public WFileSystem create(String username, Long space) throws Exception{
        String root = Paths.get(cloudDirname, username).toString();
        return new WFileSystem(root, space);
    }

    public WFileSystem load(String username) throws Exception{
        String pathname = Paths.get(cloudDirname, username, indexFilename).toString();
        return (WFileSystem) serializer.load(pathname);
    }

}
