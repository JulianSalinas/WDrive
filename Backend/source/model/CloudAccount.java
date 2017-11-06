package model;

import java.nio.file.Paths;

public class CloudAccount extends Account implements ICloud {

    private CloudFileSystem cloud;

    public CloudFileSystem getCloud() {
        return cloud;
    }

    public CloudAccount(String username, String password, Long space) throws Exception{
        super(username, password);
        this.cloud = createFileSystem(space);
    }

    private CloudFileSystem createFileSystem(Long space) throws Exception{
        String root = Paths.get(cloudDir, username).toString();
        return new CloudFileSystem(root, space);
    }

}
