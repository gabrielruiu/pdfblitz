package com.javastrike.pdfblitz.manager.model;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public enum MimeType {

    PDF("application/pdf","pdf"),
    IMAGE_JPG("image/jpg","jpg");


    private String mimeType;
    private String fileExtension;

    private MimeType(String mimeType, String fileExtension) {
        this.mimeType = mimeType;
        this.fileExtension = fileExtension;
    }

    @Override
    public String toString() {
        return mimeType;
    }
}
