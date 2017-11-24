package api;

import java.util.ArrayList;
import java.util.List;

public class Loader {

    private WDriveApi api;

    public void generateTestData(){
        List<String> usernames = generateTestUsernames();
        for (String username : usernames){
            api = new WDriveApi();
            api.createAccount(username, "1233", 4000);
            generateTestDocuments("documentos");
            generateTestDocuments("videos");
            generateTestDocuments("musica");
            generateTestDocuments("peliculas");
            generateTestDocuments("imagenes");
            generateTestDocuments("otros");
        }
    }

    private List<String> generateTestUsernames(){
        List<String> usernames = new ArrayList<>();
        usernames.add("julian");    usernames.add("maria");
        usernames.add("jesus");     usernames.add("judas");
        usernames.add("david");     usernames.add("luna");
        usernames.add("fabio");     usernames.add("karla");
        return usernames;
    }

    private void generateTestDocuments(String targetDirname){
        api.createDir(targetDirname);
        api.accessDir(targetDirname);
        for(int i = 1; i <= 10; i++)
            System.out.println(api.createFile(
                    targetDirname+String.valueOf(i)+".txt",
                    "Contenido de "+targetDirname+String.valueOf(i)));
        api.accessDir("..");
    }

}
