package model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public abstract class FileSystemNode {

    protected FileSystemNode parent;
    protected String pathname;

    private Long size;
    private Long creationTime;
    private Long lastModifiedTime;

    public FileSystemNode getParent() {
        return parent;
    }

    public String getPathname() {
        return pathname;
    }

    public Map<String, Object> getAttributes() throws Exception{
        Map<String, Object> map = new HashMap<>();
        BasicFileAttributes attributes = readAttributes();
        map.put("size", attributes.size());
        map.put("creationTime", attributes.creationTime());
        map.put("lastModifiedTime", attributes.lastModifiedTime());
        return map;
    }

    private BasicFileAttributes readAttributes() throws Exception{
        Path path = new File(pathname).toPath();
        return Files.readAttributes(path, BasicFileAttributes.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileSystemNode)) return false;
        FileSystemNode that = (FileSystemNode) o;
        return pathname.equals(that.pathname);
    }

}
