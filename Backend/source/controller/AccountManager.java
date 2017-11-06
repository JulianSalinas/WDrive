package controller;

import java.io.File;
import java.nio.file.Paths;
import model.Account;
import model.CloudAccount;
import model.ICloud;

public class AccountManager implements ICloud {

    public Account create(String username, String password, Long space) throws Exception{
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
