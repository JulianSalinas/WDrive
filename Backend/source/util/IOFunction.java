package util;

import java.io.File;

public class IOFunction implements IMessage{

    public static void mkdirs(String pathname) throws Exception{
        File file = new File(pathname);
        if(!file.isDirectory() && !file.mkdirs())
            throw new Exception(msgDirNotCreated);
    }


}
