package com.javastrike.pdfblitz.manager.model;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public enum MimeType {

    UNKNOWN("",""),
    TEXT("text/plain", "txt"),
    PDF("application/pdf","pdf"),
    IMAGE_JPEG("image/jpeg","jpg");


    private String mimeType;
    private String fileExtension;


    private MimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    private MimeType(String mimeType, String fileExtension) {
        this.mimeType = mimeType;
        this.fileExtension = fileExtension;
    }

    @Override
    public String toString() {
        return mimeType;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
