import java.io.File;

public class AccountManager {

    private String userspath;

    public AccountManager() throws Exception{
        this.userspath = "./users/";
    }

    public AccountManager(String userspath) throws Exception {

        File file = new File(userspath);

        if(!file.isDirectory())
            throw new Exception("Nombre inválido de directorio");

        else if(!file.exists())
           if(!file.mkdirs())
               throw new Exception("No se ha podido crear el directorio para administrar los usuarios");

        this.userspath = userspath;
    }

    public Account createAccount(String username, String password) throws Exception{

        Drive drive = new Drive();
        Account account = new Account(username, password, drive);
        XmlStream stream = new XmlStream();
        String filename = this.userspath + username;
        stream.save(filename, account);
        return account;

    }

    private void existsAccount(String username) throws Exception {

    }

    private void isValidPassword(String username, String password) throws Exception {

    }

}
