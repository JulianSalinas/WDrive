package model;

import java.io.File;
import java.util.Date;

public class FileSystemFile {

    protected String pathname;
    protected FileSystemFile parent;

    protected Long size;
    protected Long creationTime;
    protected Long lastModifiedTime;

    public String getPathname() {
        return pathname;
    }

    public FileSystemFile getParent() {
        return parent;
    }

    public Long getSize() {
        File file = new File(pathname);
        size = file.length();
        return size;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public Long getLastModifiedTime() {
        File file = new File(pathname);
        lastModifiedTime = file.lastModified();
        return lastModifiedTime;
    }

    public FileSystemFile(String pathname) throws Exception{
        this.creationTime = new Date().getTime();
        this.pathname = pathname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileSystemFile)) return false;
        FileSystemFile that = (FileSystemFile) o;
        return pathname.equals(that.pathname);
    }

}
