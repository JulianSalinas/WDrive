package model;

import java.io.File;
import java.nio.file.Paths;

public class FileSystem extends FileSystemDir implements IMessage {

    public FileSystem(String pathname) throws Exception{
        super(pathname);
    }

    public FileSystemFile create(String dirname) throws Exception {
        FileSystemDir parent = (FileSystemDir) navigate(dirname, "../");

        if(parent == null)
            parent = (FileSystemDir) create((new File(dirname)).getParent());

        FileSystemDir child = new FileSystemDir(dirname);
        return parent.add(child);
    }

    public FileSystemFile create(String filename, String content) throws Exception{
        FileSystemDir parent = (FileSystemDir) navigate(filename, "../");

        if(parent == null)
            parent = (FileSystemDir) create((new File(filename)).getParent());

        FileSystemFile child = new FileSystemFile(filename, content);
        return parent.add(child);
    }

    public FileSystemFile navigate(String toPathname){
        return navigate(getPath(), toPathname);
    }

    public FileSystemFile navigate(String fromPathname, String toPathname){
        if (fromPathname.equals("./"))
            return search(toPathname);
        else if(toPathname.equals("../"))
            return search((new File(fromPathname)).getParent());
        else
            return search(Paths.get(fromPathname, toPathname).toString());
    }

}
