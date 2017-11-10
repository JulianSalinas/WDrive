package controller;

import model.CloudAccount;
import model.CloudFileSystem;

public class Main {

    public static void main(String[] args) {

        long space = 20480; //bytes

        try{
            CloudManager cloudManager = new CloudManager();
            CloudAccount account = cloudManager.load("julian", "1234");
//            CloudAccount account = cloudManager.create("julian", "1234", space);
            CloudFileSystem cloud = account.getCloud();
//            cloud.create("carpeta1");
//            cloud.create("carpeta1/archivo1.txt", "hola! =)");
//            cloud.create("carpeta1/archivo2.txt", "miau!, ahora si es!");
            cloud.create("carpeta1/archivo3.txt", "Despues de crear la cuenta");
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            System.out.println("Si la cuenta ya existe, borrela para volverla a crear");
        }
    }
}
