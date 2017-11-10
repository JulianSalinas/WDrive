package model;

import java.nio.file.Paths;

public class CloudFileSystem extends FileSystem implements ICloud{

    private Long space;
    private String driveDirname;
    private String sharedDirname;
    private String indexFilename;

    public CloudFileSystem(String pathname, Long space) throws Exception{
        super(pathname);
        this.space = space;
        this.driveDirname = Paths.get(getPath(), ICloud.driveDirname).toString();
        this.sharedDirname = Paths.get(getPath(), ICloud.sharedDirname).toString();
        this.indexFilename = Paths.get(getPath(), ICloud.indexFilename).toString();
        createDirs();
    }

    private void createDirs() throws Exception{
        super.create(driveDirname);
        super.create(sharedDirname);
        updateDirs(this);
    }

    public FileSystemFile updateDirs(FileSystemFile file) throws Exception{
        space -= file.update().getSize();
        XmlStream stream = new XmlStream();
        stream.save(indexFilename, this.update());
        return file;
    }

    @Override
    public FileSystemFile create(String dirname) throws Exception {
        dirname = Paths.get(driveDirname, dirname).toString();
        if(space > 0)
            return updateDirs(super.create(dirname));
        throw new Exception(msgNotEnoughSpace);
    }

    @Override
    public FileSystemFile create(String filename, String content) throws Exception{
        filename = Paths.get(driveDirname, filename).toString();
        if(space >= content.getBytes().length)
            return updateDirs(super.create(filename, content));
        throw new Exception(msgNotEnoughSpace);
    }

}
