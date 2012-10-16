package com.javastrike.pdfblitz.manager.model;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class TextDocument extends Document {


    public TextDocument() {
    }

    public TextDocument(byte[] content, String name) {
        super(content, name, MimeType.TEXT);
    }

    @Override
    public MimeType getMimeType() {
        return MimeType.TEXT;
    }

    @Override
    public void setMimeType(MimeType mimeType) {
    }
}
