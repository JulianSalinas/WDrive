package api;

public class Main {

    public static void main(String[] args) {

        WDriveApi api = new WDriveApi();

        // System.out.println(api.createAccount("julian", "1233", 100L));
        System.out.println("\n" + api.loadAccount("julian", "1233"));
        System.out.println("\n" + api.getCurrentDirname());

        System.out.println("\n" + api.createDir("videos"));
        System.out.println("\n" + api.createDir("musica"));
        System.out.println("\n" + api.createDir("documentos"));

        api.accessDir("documentos");
        System.out.println("\n" + api.getCurrentDirname());

        System.out.println("\n" + api.createFile("tarea1.txt", "Cóntenido de la tarea 1"));
        System.out.println("\n" + api.createFile("tarea2.txt", "Cóntenido de la tarea 2"));

        api.accessDir("..");
        System.out.println("\n" + api.getCurrentDirname());


    }
}
