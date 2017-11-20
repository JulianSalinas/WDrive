package controller;

import model.*;
import java.io.File;
import java.nio.file.Paths;

public class CloudManager implements ICloud {

    XmlStream stream;

    public CloudManager() throws Exception {
        stream = new XmlStream();
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
        return (CloudFileSystem) stream.load(pathname);
    }

}
