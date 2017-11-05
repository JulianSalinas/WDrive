package controller;

public class Controller {

    private AccountManager accountManager;

    public Controller() throws Exception{
        accountManager = new AccountManager();
    }

    public String createDrive(String username, String password, Long space){
        return null;
    }

    public String openDrive(String username, String password){
        return null;
    }

    public String createFile(String filename, String content){
        return null;
    }

    public String createDir(String dirname){
        return null;
    }

    public String changeDir(){
        return null;
    }

    public String listDir(){
        return null;
    }

    public String modifyFile(){
        return null;
    }

    public String fileProperties(){
        return null;
    }

    public String openFile(String filename){
        return null;
    }

    public String copy(String filename1, String filename2){
        return null;
    }

    public String move(String filename1, String filename2){
        return null;
    }

    public String delete(String filename){
        return null;
    }

    public String share(String filename, String username){
        return null;
    }

}
