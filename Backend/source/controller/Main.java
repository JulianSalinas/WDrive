package controller;

import model.CloudAccount;
import model.CloudFileSystem;

public class Main {

    public static void main(String[] args) {

        long space = 20480; //bytes

        try{
            CloudManager cloudManager = new CloudManager();
            CloudAccount account = cloudManager.create("julian", "1234", space);
            CloudFileSystem cloud = account.getCloud();
            cloud.create("carpeta1");
            cloud.create("carpeta1/archivo1.txt", "hola! =)");
            cloud.create("carpeta2");
            cloud.create("archivo2.txt", "miau!, ahora si es!");
            cloud.create("carpeta2/carpeta3");
            cloud.create("carpeta2/carpeta5");
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            System.out.println("Si la cuenta ya existe, borrela para volverla a crear");
        }
    }
}
