package controller;

import java.io.File;
import model.Account;
import model.StorageAccount;
import util.XmlStream;

public class AccountManager {

    private String accountsDir;

    public AccountManager() throws Exception{
        this.accountsDir = "./accounts/";
    }

    public AccountManager(String accountsDir) throws Exception {
        File file = new File(accountsDir);
        if(!file.isDirectory() && file.mkdirs())
            throw new Exception("No se ha podido crear el directorio para administrar los usuarios");
        this.accountsDir = accountsDir;
    }

    public Account createAccount(String username, String password, Long space) throws Exception{
        Account account = new StorageAccount(username, password, space);
        this.saveAccount(account);
        return account;
    }

    public Account loadAccount(String username, String password) throws Exception{
        Account account = this.loadAccount(username);
        if(!isValidPassword(account, password))
            throw new Exception("Contraseña incorrecta");
        return account;
    }

    private Account loadAccount(String username) throws Exception{
        if(!this.existsAccount(username))
            throw new Exception("La cuenta no existe");
        XmlStream stream = new XmlStream();
        String accountDir = this.accountsDir + username;
        return (Account) stream.load(accountDir);
    }

    public void saveAccount(Account account) throws Exception{
        XmlStream stream = new XmlStream();
        String filename = this.accountsDir + account.getUsername();
        stream.save(filename, account);
    }

    private boolean existsAccount(String username){
        String accountDir = this.accountsDir + username;
        File file = new File(accountDir);
        return file.exists();
    }

    private boolean isValidPassword(Account account, String password){
        return password.equals(account.getPassword());
    }

}
