package model;

import java.io.File;

public abstract class FileSystemNode {

    protected String name;
    protected File filename;
    protected Long creationTime;
    protected Long lastModifiedTime;
    protected Long size;

    public String getName(){
        return filename.getName();
    }

    public String getPath(){
        return filename.getPath();
    }

    public String getAbsolutePath(){
        return filename.getAbsolutePath();
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public Long getLastModifiedTime() {
        return filename.lastModified();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof FileSystemFile))
            return false;
        FileSystemFile file1 = (FileSystemFile) obj;
        return filename.equals(file1.filename);
    }

    @Override
    public String toString(){
        return getPath();
    }

}
