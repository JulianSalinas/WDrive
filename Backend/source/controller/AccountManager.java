package controller;

import java.io.File;
import java.nio.file.Paths;
import model.Account;
import model.CloudAccount;
import model.IRoute;
import util.IMessage;
import util.IOFunction;
import util.XmlStream;

public class AccountManager implements IMessage, IRoute {

    public AccountManager() throws Exception{
        IOFunction.mkdirs(accountsDir);
    }

    public Account create(String username, String password, Long space) throws Exception{
        if(exists(username))
            throw new Exception(msgAccountAlreadyExists);
        Account account = new CloudAccount(username, password, space);
        save(account);
        return account;
    }

    public Account load(String username, String password) throws Exception{
        Account account = load(username);
        if(!isValidPassword(account, password))
            throw new Exception(msgIncorrectPassword);
        return account;
    }

    private Account load(String username) throws Exception{
        if(!exists(username))
            throw new Exception(msgAccountNotExists);
        XmlStream stream = new XmlStream();
        String accountDir = Paths.get(accountsDir, username).toString();
        return (Account) stream.load(accountDir);
    }

    public void save(Account account) throws Exception{
        XmlStream stream = new XmlStream();
        String filename = Paths.get(accountsDir, account.getUsername()).toString();
        stream.save(filename, account);
    }

    private boolean exists(String username){
        String accountDir = Paths.get(accountsDir, username).toString();
        File file = new File(accountDir);
        return file.exists();
    }

    private boolean isValidPassword(Account account, String password){
        return password.equals(account.getPassword());
    }

}
