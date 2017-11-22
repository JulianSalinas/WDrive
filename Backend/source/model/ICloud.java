package model;

import java.nio.file.Paths;

public interface ICloud extends IMessage {

    String cloudDirname =
            Paths.get(System.getProperty("user.home"),"Desktop", "cloud").toString();

    String indexFilename = "index.xml";
    String userFilename = "userinfo.xml";
    String driveDirname = "/drive/";
    String sharedDirname = "/shared/";

}
