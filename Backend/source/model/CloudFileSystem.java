package model;

import java.nio.file.Paths;

public class CloudFileSystem extends FileSystem implements ICloud{

    private Long totalSpace;
    private Long availableSpace;

    private String driveDirname;
    private String sharedDirname;
    private String indexFilename;

    public CloudFileSystem(String pathname, Long space) throws Exception{
        super(pathname);
        this.totalSpace = space;
        this.availableSpace = totalSpace;
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

    public String mapPath(String basename, String filename) {
        if(!Paths.get(filename).startsWith(basename))
            return Paths.get(basename, filename).toString();
        return filename;
    }

    public FileSystemFile updateDirs(FileSystemFile file) throws Exception{
        availableSpace = totalSpace - getSize();
        XmlStream stream = new XmlStream();
        stream.save(indexFilename, this.update());
        return file;
    }

    @Override
    public FileSystemFile create(String dirname) throws Exception {
        dirname = mapPath(driveDirname, dirname);
        if(availableSpace > 0)
            return updateDirs(super.create(dirname));
        throw new Exception(msgNotEnoughSpace);
    }

    @Override
    public FileSystemFile create(String filename, String content) throws Exception{
        filename = mapPath(driveDirname, filename);
        if(availableSpace >= content.getBytes().length)
            return updateDirs(super.create(filename, content));
        throw new Exception(msgNotEnoughSpace);
    }

    @Override
    public FileSystemFile navigate(String fromPathname, String toPathname) {
        fromPathname = mapPath(getPath(), fromPathname);
        return super.navigate(fromPathname, toPathname);
    }

    @Override
    public FileSystemFile delete(String filename) throws Exception{
        filename = mapPath(driveDirname, filename);
        return updateDirs(super.delete(filename));
    }

}
