package controller;

import model.*;
import java.io.File;
import java.nio.file.Paths;

public class AccountManager implements ICloud {

    private CloudManager cloudManager;

    public AccountManager() throws Exception {
        cloudManager = new CloudManager();
        File file = new File(cloudDirname);
        if (!file.isDirectory() && !file.mkdirs())
            throw new Exception(msgDirNotCreated);
    }

    public CloudAccount create(String username, String password, Long space) throws Exception{

        if(exists(username))
            throw new Exception(msgAccountAlreadyExists);

        CloudAccount account = new CloudAccount(username, password);
        CloudFileSystem fileSystem = cloudManager.create(username, space);
        account.setCloud(fileSystem);

        String filename = Paths.get(cloudDirname, username, userFilename).toString();
        XmlStream stream = new XmlStream();
        stream.save(filename, account);

        return account;
    }

    public CloudAccount load(String username, String password) throws Exception{

        if(!exists(username))
            create(username, password, 4096L);
            // throw new Exception(msgAccountNotExists);

        XmlStream stream = new XmlStream();
        String filename = Paths.get(cloudDirname, username, userFilename).toString();
        CloudAccount account = (CloudAccount) stream.load(filename);
        CloudFileSystem fileSystem = cloudManager.load(username);
        account.setCloud(fileSystem);

        if(!isValidPassword(account, password))
            throw new Exception(msgIncorrectPassword);

        return account;
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
