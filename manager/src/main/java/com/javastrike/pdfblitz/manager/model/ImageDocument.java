package com.javastrike.pdfblitz.manager.model;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ImageDocument extends Document {


    private ImageFormat imageFormat;

    public ImageDocument(ImageFormat imageFormat) {
        this.imageFormat = imageFormat;
    }

    public ImageDocument(byte[] content, String name, String MIMEtype, ImageFormat imageFormat) {
        super(content, name, MIMEtype);
        this.imageFormat = imageFormat;
    }

    public ImageFormat getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(ImageFormat imageFormat) {
        this.imageFormat = imageFormat;
    }


    public enum ImageFormat {

        JPEG,
        JPG
    }

}
