package controller;

import model.*;
import java.io.File;
import java.nio.file.Paths;

public class CloudManager implements ICloud {

    public CloudManager() throws Exception {
        File file = new File(cloudDirname);
        if (!file.isDirectory() && !file.mkdirs())
            throw new Exception(msgDirNotCreated);
    }

    public CloudFileSystem create(String username, Long space) throws Exception{
        String root = Paths.get(cloudDirname, username).toString();
        return new CloudFileSystem(root, space);
    }

    public CloudFileSystem load(String username) throws Exception{
        XmlStream stream = new XmlStream();
        String pathname = Paths.get(cloudDirname, username, indexFilename).toString();
        return (CloudFileSystem) stream.load(pathname);
    }

}
