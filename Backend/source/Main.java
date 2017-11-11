import model.*;
import controller.*;

import java.io.File;
import java.lang.System;
import org.apache.commons.io.FileUtils;

public class Main {

    public static void main(String[] args) {

        long space = 20480; //bytes

        try{

            /* Creación de la cuente */
            AccountManager accountManager = new AccountManager();
            CloudAccount account = accountManager.load("aquiles", "1234");
//            CloudAccount account = accountManager.create("aquiles", "1234", space);

            /* Creación del filesytem */
            CloudFileSystem cloud = account.getCloud();
            cloud.create("archivo1.txt", "hola!! =)");
            cloud.create("archivo2.txt", "miau!, ahora si es!");
            cloud.create("carpeta2/archivo3.txt", "Despues de crear la cuenta");

            /* Navegación */
            System.out.println(cloud.navigate("drive/archivo1.txt", "../"));
            System.out.println(cloud.navigate("drive", "carpeta2"));
            FileSystemFile file = cloud.navigate("drive/", "archivo1.txt");
            System.out.println(FileUtils.readFileToString(new File(file.getPath())));

            /* Borrado */
            cloud.delete("archivo2.txt");
            cloud.delete("carpeta2");

        }

        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }
}
