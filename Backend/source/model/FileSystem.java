package model;

import java.nio.file.Paths;

public abstract class FileSystem extends FileSystemDir implements IMessage {

    protected Long totalSpace;
    protected Long availableSpace;

    public Long getTotalSpace() {
        return totalSpace;
    }

    public Long getAvailableSpace() {
        return availableSpace;
    }

    public FileSystem(String pathname, Long space) throws Exception{
        super(pathname);
        this.totalSpace = space;
        this.availableSpace = totalSpace;
    }

    private String mapName(FileSystemDir currentDir, String dirname){
        String base = currentDir.getAbsolutePath();
        return Paths.get(base, dirname).toString();
    }

    public FileSystemDir create(FileSystemDir currentDir, String dirname) throws Exception{
        if(availableSpace > 0)
            return currentDir.create(dirname);

        throw new Exception(msgNotEnoughSpace);
    }

    public FileSystemFile create(FileSystemDir currentDir, String filename, String content) throws Exception{
        if(availableSpace >= content.getBytes().length)
            return currentDir.create(filename, content);
        throw new Exception(msgNotEnoughSpace);
    }

    public FileSystemFile delete(FileSystemDir currentDir, String filename) throws Exception{
        FileSystemFile file = currentDir.getFile(filename);
        return currentDir.remove(file).delete();
    }

    public FileSystemFile copy(FileSystemFile clipboard, FileSystemDir targetDir) throws Exception{
        if(availableSpace >=  clipboard.getSize()){
            targetDir.add(clipboard);
            return clipboard.copy(targetDir);
        }
        throw new Exception(msgNotEnoughSpace);
    }

    public FileSystemFile move(FileSystemFile clipboard, FileSystemDir sourceDir, FileSystemDir targetDir) throws Exception{
        sourceDir.remove(clipboard);
        clipboard = clipboard.move(targetDir);
        return clipboard.copy(targetDir);
    }

}
