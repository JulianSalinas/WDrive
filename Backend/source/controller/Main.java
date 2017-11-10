package controller;

import model.CloudAccount;
import model.CloudFileSystem;

public class Main {

    public static void main(String[] args) {

        long space = 20480; //bytes

        try{
            AccountManager accountManager = new AccountManager();
            CloudAccount account = accountManager.load("aquiles", "1234");
//            CloudAccount account = accountManager.create("aquiles", "1234", space);
            CloudFileSystem cloud = account.getCloud();
            cloud.create("carpeta1/carpeta2/archivo1.txt", "hola!! =)");
            cloud.create("carpeta1/archivo2.txt", "miau!, ahora si es!");
            cloud.create("carpeta1/archivo3.txt", "Despues de crear la cuenta");
            cloud.create("carpeta2/archivo4.txt", "Contenido del archivo 4");
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
