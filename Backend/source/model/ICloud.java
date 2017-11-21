package model;

public interface ICloud extends IMessage {

    String cloudDirname = System.getProperty("user.home") + "Desktop";
    String indexFilename = "index.xml";
    String userFilename = "userinfo.xml";
    String driveDirname = "/drive/";
    String sharedDirname = "/shared/";

}
