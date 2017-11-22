package api;

public class Main {

    public static void main(String[] args) {

        WDriveApi api = new WDriveApi();

        // Si es la primera vez que se corre, se debe descomentar la primera linea
        // System.out.println(api.createAccount("julian", "1233", 100L));
        System.out.println("\n" + api.loadAccount("julian", "1233"));
        System.out.println("\n" + api.getCurrentDirname());

        // Se crean directorios en la carpeta drive del usuario (drive es donde se coloca automaticamente al iniciar)
        System.out.println("\n" + api.createDir("videos"));
        System.out.println("\n" + api.createDir("musica"));
        System.out.println("\n" + api.createDir("documentos"));

        // Se accede dentro de la carpeta documentos
        api.accessDir("documentos");
        System.out.println("\n" + api.getCurrentDirname());

        // Debe mostrar que todavia no existe
        System.out.println("\n" + api.fileExists("tarea1.txt"));

        // Se crean 2 archivos dentro de documentos
        System.out.println("\n" + api.createFile("tarea1.txt", "Cóntenido de la tarea 1"));
        System.out.println("\n" + api.createFile("tarea2.txt", "Cóntenido de la tarea 2"));

        // Ahora debe mostrar que si existe
        System.out.println("\n" + api.fileExists("tarea1.txt"));

        // Se muestran los archivos dentro del directorio actual, es decir, en documentos
        System.out.println("\n" + api.listFiles());

        // Se devuelve al directorio padre (a la carpeta drive)
        System.out.println("\n" + api.accessDir(".."));
        System.out.println("\n" + api.getCurrentDirname());


    }
}
