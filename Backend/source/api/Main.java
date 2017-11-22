package api;

public class Main {

    public static void main(String[] args) {

        long space = 20480; //bytes

        try{
            WDriveApi api = new WDriveApi();
            System.out.println(api.createAccount("julian", "1233", 100L));




//            /* Creación de la cuente */
//            AccountManager accountManager = new AccountManager();
//            WAccount account = accountManager.load("aquiles", "1234");
//            WAccount account2 = accountManager.load("julian", "1234");
////            WAccount account = accountManager.create("aquiles", "1234", space);
//
//            /* Creación del filesytem */
//            WFileSystem controller = account.getCloud();
//            System.out.println(controller.create("archivo1.txt", "hola!! =)"));
//            System.out.println(controller.create("archivo2.txt", "miau!, ahora si es!"));
//            System.out.println(controller.create("carpeta1/archivo3.txt", "Despues de crear la cuenta"));
//            System.out.println(controller.create("carpeta2"));
//
//            /* Navegación */
//            System.out.println(controller.navigate("drive/archivo1.txt", "../"));
//            System.out.println(controller.navigate("drive", "carpeta2"));
//            FileSystemFile file = controller.navigate("drive/", "archivo1.txt");
//            System.out.println(FileUtils.readFileToString(new File(file.getPath())));
//
//            /* Borrado */
//            System.out.println(controller.delete("archivo2.txt"));
//            System.out.println(controller.delete("carpeta2"));
//
//            /* Copiar */
//            System.out.println(controller.copy("archivo1.txt", "carpeta1"));
//            System.out.println(controller.copy("carpeta1", "carpeta2"));
//
//            /* Mover */
//            System.out.println(controller.move("archivo1.txt", "carpeta3"));
//            System.out.println(controller.move("carpeta1", "carpeta3"));
//
//            /* Compartir */
//            controller.share("carpeta3/archivo1.txt", account2.getCloud());
//            controller.share("carpeta2", account2.getCloud());

        }

        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }
}
