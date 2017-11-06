package controller;

import java.io.File;
import java.nio.file.Paths;
import model.CloudAccount;
import model.ICloud;

public class CloudManager implements ICloud {

    public CloudManager() throws Exception {
        File file = new File(cloudDir);
        if (!file.isDirectory() && !file.mkdirs())
            throw new Exception(msgDirNotCreated);
    }

    public CloudAccount create(String username, String password, Long space) throws Exception{
        if(exists(username))
            throw new Exception(msgAccountAlreadyExists);
        return new CloudAccount(username, password, space);
    }

    private boolean exists(String username){
        String dir = Paths.get(cloudDir, username).toString();
        File file = new File(dir);
        return file.exists();
    }

}
