package model;

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

    public FileSystemFile delete(FileSystemDir currentDir, String filename, Boolean virtual) throws Exception{
        FileSystemFile file = currentDir.getFile(filename);
        if(virtual)
            return currentDir.remove(file);
        return currentDir.remove(file).delete();
    }

    public FileSystemFile copy(FileSystemFile clipboard, FileSystemDir targetDir) throws Exception{
        if(availableSpace >=  clipboard.getSize()){
            FileSystemFile file = clipboard.copy(targetDir);
            return targetDir.add(file);
        }
        throw new Exception(msgNotEnoughSpace);
    }

    public FileSystemFile move(FileSystemFile clipboard, FileSystemDir sourceDir, FileSystemDir targetDir) throws Exception{
        sourceDir.remove(clipboard);
        clipboard = clipboard.move(targetDir);
        return targetDir.add(clipboard);
    }

}
