package model;

import util.XmlSerializer;

import java.nio.file.Paths;

public class WFileSystem extends FileSystem implements ICloud {

    private String driveDirname;
    private String sharedDirname;
    private String indexFilename;

    public WFileSystem(String pathname, Long space) throws Exception{
        super(pathname, space);
        this.driveDirname = Paths.get(getPath(), ICloud.driveDirname).toString();
        this.sharedDirname = Paths.get(getPath(), ICloud.sharedDirname).toString();
        this.indexFilename = Paths.get(getPath(), ICloud.indexFilename).toString();
        createDirs();
    }

    private void createDirs() throws Exception{
        add(new FileSystemDir(driveDirname, false));
        add(new FileSystemDir(sharedDirname, true));
        updateDirs(this);
    }

    private String mapPath(FileSystemDir currentDir, String dirname){
        String base = currentDir.getAbsolutePath();
        return Paths.get(base, dirname).toString();
    }

    public FileSystemFile updateDirs(FileSystemFile file) throws Exception{
        FileSystemDir driveDir = (FileSystemDir) getFile("drive");
        availableSpace = totalSpace - driveDir.getSize();
        new XmlSerializer().save(indexFilename, this.update());
        return file;
    }

    @Override
    public FileSystemDir create(FileSystemDir currentDir, String dirname) throws Exception{
        dirname = mapPath(currentDir, dirname);
        return (FileSystemDir) updateDirs(super.create(currentDir, dirname));
    }

    @Override
    public FileSystemFile create(FileSystemDir currentDir, String filename, String content) throws Exception{
        filename = mapPath(currentDir, filename);
        return updateDirs(super.create(currentDir, filename, content));
    }

    @Override
    public FileSystemFile delete(FileSystemDir currentDir, String filename, Boolean virtual) throws Exception{
        return updateDirs(super.delete(currentDir, filename, virtual));
    }

    @Override
    public FileSystemFile copy(FileSystemFile clipboard, FileSystemDir targetDir) throws Exception{
        return updateDirs(super.copy(clipboard, targetDir));
    }

    @Override
    public FileSystemFile move(FileSystemFile clipboard, FileSystemDir sourceDir, FileSystemDir targetDir) throws Exception{
        return updateDirs(super.move(clipboard, sourceDir, targetDir));
    }

    public FileSystemFile share(FileSystemFile clipboard, WFileSystem targetFS) throws Exception{
        FileSystemDir sharedDir = (FileSystemDir) targetFS.getFile("shared");
        sharedDir.add(clipboard);
        return targetFS.updateDirs(clipboard);
    }

}
