package controller;

public class WDriveMessage {

    public static String OK = "OK";
    public static String ERROR ="ERROR";

    private String status;
    private Object content;

    public String getStatus() {
        return status;
    }

    public Object getContent() {
        return content;
    }

    public WDriveMessage(String status, Object content){
        this.status = status;
        this.content = content;
    }

}
