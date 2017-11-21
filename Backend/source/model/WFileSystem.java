package model;

import util.XmlSerializer;

import java.nio.file.Paths;

public class WFileSystem extends FileSystem implements ICloud {

    private Long totalSpace;
    private Long availableSpace;

    private String driveDirname;
    private String sharedDirname;
    private String indexFilename;

    public WFileSystem(String pathname, Long space) throws Exception{
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
        add(new FileSystemDir(sharedDirname, true));
        updateDirs(this);
    }

    public String mapPath(String basename, String filename) {
        if(!Paths.get(filename).startsWith(basename))
            return Paths.get(basename, filename).toString();
        return filename;
    }

    public FileSystemFile updateDirs(FileSystemFile file) throws Exception{
        FileSystemDir driveDir = (FileSystemDir) search(driveDirname);
        availableSpace = totalSpace - driveDir.getSize();
        XmlSerializer stream = new XmlSerializer();
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

    @Override
    public FileSystemFile copy(String filename, String dirname) throws Exception{
        filename = mapPath(driveDirname, filename);
        dirname = mapPath(driveDirname, dirname);
        FileSystemFile file = search(filename);
        if(file == null)
            throw new Exception(msgFileNotExists);
        if(availableSpace >=  file.getSize())
            return updateDirs(super.copy(filename, dirname));
        throw new Exception(msgNotEnoughSpace);
    }

    @Override
    public FileSystemFile move(String filename, String dirname) throws Exception{
        filename = mapPath(driveDirname, filename);
        dirname = mapPath(driveDirname, dirname);
        return updateDirs(super.move(filename, dirname));
    }


    @Override
    public Boolean exists(String filename) throws Exception{
        filename = mapPath(driveDirname, filename);
        return super.exists(filename);
    }

    public FileSystemFile share(String filename, WFileSystem target) throws Exception{
        filename = mapPath(driveDirname, filename);
        FileSystemFile file = search(filename);
        if(file == null)
            throw new Exception(msgFileNotExists);
        FileSystemDir sharedDir = (FileSystemDir) target.search(target.sharedDirname);
        return target.updateDirs(sharedDir.add(file));
    }

}
