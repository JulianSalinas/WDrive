package model;

import java.nio.file.Paths;

public class CloudFileSystem extends FileSystem{

    private Long space;

    public CloudFileSystem(String pathname, Long space) throws Exception{
        super(pathname);
        this.space = space;
        createDirs();
        updateDirs();
    }

    private void createDirs() throws Exception{
        create(Paths.get(root.getPathname(), sharedDir).toString());
        create(Paths.get(root.getPathname(), driveDir).toString());
    }

    public void updateDirs() throws Exception{
        XmlStream stream = new XmlStream();
        String pathname = Paths.get(root.getPathname(), indexName).toString();
        stream.save(pathname, root);
    }

    @Override
    public FileSystemNode create(String dirname) throws Exception {
        if(space > 0)
            return super.create(dirname);
        throw new Exception(msgNotEnoughSpace);
    }

    @Override
    public FileSystemNode create(String filename, String content) throws Exception{
        if(space >= content.getBytes().length)
            return super.create(filename, content);
        throw new Exception(msgNotEnoughSpace);
    }

}
