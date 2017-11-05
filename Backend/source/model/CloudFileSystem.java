package model;

import java.io.File;
import java.nio.file.Paths;

public class CloudFileSystem extends FileSystem implements IRoute {

    private Long space;
    private String sharedDirname;
    private String driveDirname;

    public CloudFileSystem(String pathname, Long space) throws Exception{
        super(pathname);
        this.space = space;
        createBaseDirs();
    }

    private void createBaseDirs() throws Exception{
        create(Paths.get(root.getPathname(), sharedDir).toString());
        create(Paths.get(root.getPathname(), driveDir).toString());
    }

    public FileSystemNode search(String pathname){
        return null;
    }

    public Object create(String dirname) throws Exception {
        File file = (File) super.create(dirname);
        return file;
    }

    public String create(String filename, Object content) {
        return null;
    }

}
