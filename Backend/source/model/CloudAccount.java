package model;

import java.nio.file.Paths;

public class CloudAccount extends Account {

    private CloudFileSystem cloud;

    public CloudFileSystem getCloud() {
        return cloud;
    }

    public CloudAccount(String username, String password, Long space) throws Exception{
        super(username, password);
        this.cloud = createCloudFileSystem(space);
    }

    private CloudFileSystem createCloudFileSystem(Long space) throws Exception{
        String root = Paths.get(cloudDir, username).toString();
        return new CloudFileSystem(root, space);
    }

}
