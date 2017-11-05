package view;

import controller.AccountManager;

public class Main {

    public static void main(String[] args) {

        long space = 4096*4; //bytes

        try{
            AccountManager accountManager = new AccountManager();
            accountManager.createAccount("julian", "1234", space);
        }
        catch (Exception e){
            System.out.println("Error: " + e);
        }

    }
}
