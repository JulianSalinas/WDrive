package controller;

import util.XmlMessage;
import util.XmlSerializer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/helloworld")
public class HelloWorld {

    XmlSerializer serializer = new XmlSerializer();

    @GET
    @Path("test")
    public String test() {
        XmlMessage test = new XmlMessage(XmlMessage.OK, "Este es un mensaje de Julian el crack");
        String msg = serializer.toXML(test);
        return msg;
    }

}