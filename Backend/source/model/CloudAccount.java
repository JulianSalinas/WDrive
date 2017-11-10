package model;

import java.nio.file.Paths;

public class CloudAccount extends Account implements ICloud{

    private CloudFileSystem cloud;

    public CloudFileSystem getCloud() {
        return cloud;
    }

    public CloudAccount(String username, String password) throws Exception{
        super(username, password);
        this.cloud = loadFileSystem();
    }

    public CloudAccount(String username, String password, Long space) throws Exception{
        super(username, password);
        this.cloud = createFileSystem(space);
    }

    private CloudFileSystem createFileSystem(Long space) throws Exception{
        String root = Paths.get(cloudDirname, username).toString();
        return new CloudFileSystem(root, space);
    }

    private CloudFileSystem loadFileSystem() throws Exception{
        XmlStream stream = new XmlStream();
        String pathname = Paths.get(cloudDirname, username, indexFilename).toString();
        return (CloudFileSystem) stream.load(pathname);
    }

}
