import controller.AccountManager;
import model.CloudAccount;
import model.CloudFileSystem;
import model.FileSystemFile;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        long space = 20480; //bytes

        try{

            /* Creación de la cuente */
            AccountManager accountManager = new AccountManager();
            CloudAccount account = accountManager.load("aquiles", "1234");
            CloudAccount account2 = accountManager.load("julian", "1234");
//            CloudAccount account = accountManager.create("aquiles", "1234", space);

            /* Creación del filesytem */
            CloudFileSystem cloud = account.getCloud();
            System.out.println(cloud.create("archivo1.txt", "hola!! =)"));
            System.out.println(cloud.create("archivo2.txt", "miau!, ahora si es!"));
            System.out.println(cloud.create("carpeta1/archivo3.txt", "Despues de crear la cuenta"));
            System.out.println(cloud.create("carpeta2"));

            /* Navegación */
            System.out.println(cloud.navigate("drive/archivo1.txt", "../"));
            System.out.println(cloud.navigate("drive", "carpeta2"));
            FileSystemFile file = cloud.navigate("drive/", "archivo1.txt");
            System.out.println(FileUtils.readFileToString(new File(file.getPath())));

            /* Borrado */
            System.out.println(cloud.delete("archivo2.txt"));
            System.out.println(cloud.delete("carpeta2"));

            /* Copiar */
            System.out.println(cloud.copy("archivo1.txt", "carpeta1"));
            System.out.println(cloud.copy("carpeta1", "carpeta2"));

            /* Mover */
            System.out.println(cloud.move("archivo1.txt", "carpeta3"));
            System.out.println(cloud.move("carpeta1", "carpeta3"));

            /* Compartir */
            cloud.share("carpeta3/archivo1.txt", account2.getCloud());
            cloud.share("carpeta2", account2.getCloud());

        }

        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }
}
