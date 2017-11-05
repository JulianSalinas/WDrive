package view;

import controller.AccountManager;
import model.CloudAccount;
import model.CloudFileSystem;

public class Main {

    public static void main(String[] args) {

        long space = 4096*4; //bytes

        try{
            AccountManager accountManager = new AccountManager();
            CloudAccount account = (CloudAccount) accountManager.create("julian", "1234", space);
            CloudFileSystem cloud = account.getCloud();
        }
        catch (Exception e){
            System.out.println("Error: " + e);
        }

    }
}
