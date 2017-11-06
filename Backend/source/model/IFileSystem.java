package model;

public interface IFileSystem extends ICloud {

    FileSystemNode create(String dirname) throws Exception;
    FileSystemNode create(String filename, String content) throws Exception;

}
