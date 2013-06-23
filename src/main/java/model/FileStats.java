package model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 19.06.13
 * Time: 21:25
 * To change this template use File | Settings | File Templates.
 */
public class FileStats {

    private String fileName;
    private String filePath;
    private Long fileSize;
    private boolean readOnly;
    private Date lastModified;

    public FileStats(String fileName, String filePath, Long fileSize, boolean readOnly, Date lastModified) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.readOnly = readOnly;
        this.lastModified = lastModified;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
