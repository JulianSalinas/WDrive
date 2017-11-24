package controller;

import model.ICloud;
import model.WAccount;
import model.WFileSystem;
import util.XmlSerializer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AccountManager implements ICloud {

    private XmlSerializer serializer;
    private FileSystemManager fileSystemManager;

    public AccountManager() throws Exception {
        serializer = new XmlSerializer();
        fileSystemManager = new FileSystemManager();
        createDirs();
    }

    private void createDirs() throws Exception{
        File file = new File(cloudDirname);
        if (!file.isDirectory() && !file.mkdirs())
            throw new Exception(msgDirNotCreated);
    }

    public Path userInfoFilename(String username) {
        Path path = Paths.get(cloudDirname, username, userFilename);
        return path;
    }

    public WAccount create(String username, String password, Long space) throws Exception{
        if(exists(username))
            throw new Exception(msgAccountAlreadyExists);

        WFileSystem cloud = fileSystemManager.create(username, space);
        WAccount account = new WAccount(username, password, cloud);
        serializer.save(userInfoFilename(username), account);

        return account;
    }

    public WAccount load(String username, String password) throws Exception {
        if (!exists(username))
            throw new Exception(msgAccountNotExists);

        WFileSystem cloud = fileSystemManager.load(username);
        WAccount account = (WAccount) serializer.load(userInfoFilename(username));
        account.setCloud(cloud);

        if(!isValidPassword(account, password))
            throw new Exception(msgIncorrectPassword);

        return account;
    }

    public boolean exists(String username){
        Path path = userInfoFilename(username);
        return path.toFile().exists();
    }

    private boolean isValidPassword(WAccount account, String password){
        return password.equals(account.getPassword());
    }

}
