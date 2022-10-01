package org.xujin.janus.client.cmo;

/**
 * @author: ganshitao
 * @date: 2020/5/25
 */
public class SendFileDTO {
    /**
     * filterName
     * eg；AuthFilter.java、 LoginFilter.java、CookiePredicate.java
     */
    private String fileName;

    /**
     * fileType only support Filter and Predicate
     */
    private String fileType;

    private String fileContent;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
