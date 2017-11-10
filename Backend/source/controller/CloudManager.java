package controller;

import java.io.File;
import java.nio.file.Paths;
import model.CloudAccount;
import model.ICloud;
import model.XmlStream;

public class CloudManager implements ICloud {

    public CloudManager() throws Exception {
        File file = new File(cloudDirname);
        if (!file.isDirectory() && !file.mkdirs())
            throw new Exception(msgDirNotCreated);
    }

    public CloudAccount create(String username, String password, Long space) throws Exception{
        if(exists(username))
            throw new Exception(msgAccountAlreadyExists);
        return new CloudAccount(username, password, space);
    }

    public CloudAccount load(String username, String password) throws Exception{
        if(!exists(username))
            throw new Exception(msgAccountNotExists);
        return new CloudAccount(username, password);
    }

    private boolean exists(String username){
        String dir = Paths.get(cloudDirname, username).toString();
        File file = new File(dir);
        return file.exists();
    }

    private boolean isValidPassword(CloudAccount account, String password){
        return password.equals(account.getPassword());
    }

}
