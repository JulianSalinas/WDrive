package api;

public class Main {

    public static void main(String[] args) {
/*
        WDriveApi api = new WDriveApi();

        // Si es la primera vez que se corre, se debe descomentar la primera linea
        System.out.println(api.createAccount("julian", "1233", 100L));
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
        System.out.println("\n" + api.createFile("tarea1.txt", "Contenido de la tarea 1"));
        System.out.println("\n" + api.createFile("tarea2.txt", "Contenido de la tarea 2"));

        // Ahora debe mostrar que si existe
        System.out.println("\n" + api.fileExists("tarea1.txt"));

        // Obtenemos un archivo que no existe
        System.out.println("\n" + api.searchFile("tarea3.txt"));

        // Obtenemos un archivo que si existe
        System.out.println("\n" + api.searchFile("tarea1.txt"));

        // Se muestran los archivos dentro del directorio actual, es decir, en documentos
        System.out.println("\n" + api.listFiles());

        // Se devuelve al directorio padre (a la carpeta drive)
        System.out.println("\n" + api.accessDir(".."));
        System.out.println("\n" + api.getCurrentDirname());

        // Ingresamos a la carpeta documentos, copiamos un archivo, salimos de la carpeta,
        // Se ingresa a la carpeta videos y luego se pega el archivo
        api.accessDir("documentos");
        api.copyFile("tarea1.txt");
        api.accessDir("..");
        api.accessDir("videos");
        System.out.println("\n" + api.pasteFile());

        // Se va a mover el archivo pegado a la carpeta musica
        api.cutFile("tarea1.txt");
        api.accessDir("..");
        api.accessDir("musica");
        System.out.println("\n" + api.pasteFile());

        // Obtenemos el espacio total y el espacio disponible de el filesystem
        System.out.println("\n" + api.getTotalSpace());
        System.out.println("\n" + api.getAvailableSpace());

        // En este momemnto quedan pocos bytes libres, por tanto, con el siguiente archivo se
        // mostra un error de espacio insuficiente
        System.out.println("\n" + api.createFile("video1",
                "Cuenta la historia de un mago\n" +
                "Que un día en su bosque encantado lloró\n" +
                "Porque a pesar de su magia\n" +
                "No había podido encontrar el amor… "));

        // Eliminamos el archivo la carpeta musica con el archivo adentro
        api.accessDir("..");
        System.out.println("\n" + api.deleteFile("musica"));

        // Se va a crear un nuevo usuario con el se va a compartir la carpeta documentos
        WDriveApi api2 = new WDriveApi();
        api2.createAccount("aquiles", "1233", 50);
        api2.loadAccount("aquiles", "1233");
        api.shareFile("documentos", "aquiles");

        // Se confirma que ;a carpeta este dentro de la carpeta compartida del usuario nuevo
        api2.accessDir("..");

        // Como por defecto el directorio actual es drive se debe ingresar a la raiz y luego al directorio shared
        System.out.println("\n" + api2.accessDir("shared"));
        System.out.println("\n" + api2.accessDir("documentos"));

        // Leer el contenido de un archivo, en este caso se encuentra en la carpeta compartida
        System.out.println("\n" + api2.openFile("tarea1.txt"));

        // Ahora se borra una carpeta que este en la carpeta shared
        api2.accessDir("..");
        System.out.println("\n" + api2.getCurrentDirname());
        api2.deleteFile("documentos");

        System.out.println("\n" + api.cutFile("documentos"));
        System.out.println("\n" + api.accessDir("videos"));
        System.out.println("\n" + api.pasteFile());

        api.copyFile("documentos");
        api.accessDir("..");
        api.createDir("temporal");
        api.accessDir("temporal");
        api.pasteFile();
*/

        Loader loader = new Loader();
        loader.generateTestData();
    }

}

