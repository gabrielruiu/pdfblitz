package com.javastrike.pdfblitz.manager.model;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ImageDocument extends Document {

    public ImageDocument() {
    }

    public ImageDocument(byte[] content, String name) {
        super(content, name, MimeType.IMAGE_JPEG);
    }

    @Override
    public MimeType getMimeType() {
        return MimeType.IMAGE_JPEG;
    }

    @Override
    public void setMimeType(MimeType mimeType) {
    }
}
