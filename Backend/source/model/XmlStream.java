package model;

import java.io.File;
import java.io.IOException;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.io.FileUtils;

public class XmlStream extends XStream {

    public XmlStream(){
        super();
        XStream.setupDefaultSecurity(this);
        this.autodetectAnnotations(true);
        this.allowTypes(new Class[]{
                CloudAccount.class,
                CloudFileSystem.class,
                FileSystemDir.class,
                FileSystemFile.class
        });
    }

    public Object load(String filename) throws IOException {
        String xml = FileUtils.readFileToString(new File(filename));
        return this.fromXML(xml);
    }

    public String save(String filename, Object content) throws IOException{
        String xml = this.toXML(content);
        if(!filename.endsWith(".xml")) filename += ".xml";
        FileUtils.writeStringToFile(new File(filename), xml, false);
        return xml;
    }

}
