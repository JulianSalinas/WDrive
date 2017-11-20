package controller;

import model.CloudAccount;
import model.CloudFileSystem;
import model.ICloud;
import model.XmlStream;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AccountManager implements ICloud {

    private XmlStream stream;
    private CloudManager cloudManager;

    public AccountManager() throws Exception {
        stream = new XmlStream();
        cloudManager = new CloudManager();
        File file = new File(cloudDirname);
        if (!file.isDirectory() && !file.mkdirs())
            throw new Exception(msgDirNotCreated);
    }

    public Path userInfoFilename(String username) {
        Path path = Paths.get(cloudDirname, username, userFilename);
        return path;
    }

    public CloudAccount create(String username, String password, Long space) throws Exception{
        if(exists(username))
            throw new Exception(msgAccountAlreadyExists);

        CloudFileSystem cloud = cloudManager.create(username, space);
        CloudAccount account = new CloudAccount(username, password, cloud);
        stream.save(userInfoFilename(username), account);
        return account;
    }

    public CloudAccount load(String username, String password) throws Exception {
        if (!exists(username))
            create(username, password, 4096L);
            // throw new Exception(msgAccountNotExists);

        CloudFileSystem cloud = cloudManager.load(username);
        CloudAccount account = (CloudAccount) stream.load(userInfoFilename(username));
        account.setCloud(cloud);

        if(!isValidPassword(account, password))
            throw new Exception(msgIncorrectPassword);

        return account;
    }

    private boolean exists(String username){
        Path path = userInfoFilename(username);
        return path.toFile().exists();
    }

    private boolean isValidPassword(CloudAccount account, String password){
        return password.equals(account.getPassword());
    }

}
