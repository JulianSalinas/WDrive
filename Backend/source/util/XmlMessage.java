package util;

public class XmlMessage {

    public static String OK = "OK";
    public static String ERROR ="ERROR";

    private String status;
    private String content;

    public String getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public XmlMessage(String status, String content){
        this.status = status;
        this.content = content;
    }

}
