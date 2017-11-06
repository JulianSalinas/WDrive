package model;

import java.nio.file.Paths;

public class CloudFileSystem extends FileSystem{

    private Long space;

    public CloudFileSystem(String pathname, Long space) throws Exception{
        super(pathname);
        this.space = space;
        createDirs();
    }

    private void createDirs() throws Exception{
        super.create(Paths.get(root.getPathname(), sharedDir).toString());
        super.create(Paths.get(root.getPathname(), driveDir).toString());
    }

    public FileSystemNode updateDirs(FileSystemNode node) throws Exception{
        XmlStream stream = new XmlStream();
        String pathname = Paths.get(root.getPathname(), indexName).toString();
        stream.save(pathname, root);
        return node;
    }

    @Override
    public FileSystemNode create(String dirname) throws Exception {
        dirname = Paths.get(root.getPathname(), driveDir, dirname).toString();
        if(space > 0)
            return updateDirs(super.create(dirname));
        throw new Exception(msgNotEnoughSpace);
    }

    @Override
    public FileSystemNode create(String filename, String content) throws Exception{
        filename = Paths.get(root.getPathname(), driveDir, filename).toString();
        if(space >= content.getBytes().length)
            return updateDirs(super.create(filename, content));
        throw new Exception(msgNotEnoughSpace);
    }

}
