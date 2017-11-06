package model;

import java.nio.file.Paths;

public class CloudFileSystem extends FileSystem implements ICloud {

    private Long space;

    public CloudFileSystem(String pathname, Long space) throws Exception{
        super(pathname);
        this.space = space;
        createDirs();
    }

    private void createDirs() throws Exception{
        super.create(Paths.get(pathname, sharedDir).toString());
        super.create(Paths.get(pathname, driveDir).toString());
        updateDirs(this);
    }

    public FileSystemFile updateDirs(FileSystemFile node) throws Exception{
        space -= node.getSize();
        XmlStream stream = new XmlStream();
        String index = Paths.get(pathname, indexName).toString();
        stream.save(index, this);
        return node;
    }

    @Override
    public FileSystemFile create(String dirname) throws Exception {
        dirname = Paths.get(pathname, driveDir, dirname).toString();
        if(space > 0)
            return updateDirs(super.create(dirname));
        throw new Exception(msgNotEnoughSpace);
    }

    @Override
    public FileSystemFile create(String filename, String content) throws Exception{
        filename = Paths.get(pathname, driveDir, filename).toString();
        if(space >= content.getBytes().length)
            return updateDirs(super.create(filename, content));
        throw new Exception(msgNotEnoughSpace);
    }

}
