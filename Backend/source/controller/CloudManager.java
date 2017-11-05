package controller;

import model.WFileSys;

public class CloudManager {

    private String cloudDir;
    private WFileSys fileSystem;

    public CloudManager() throws Exception{
        this.cloudDir = "./cloud/";
    }

}
