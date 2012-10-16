package com.javastrike.pdfblitz.manager.model;

public class Document {


    private byte[] content;
    private String name;
    private MimeType mimeType;

    public Document() {
    }

    public Document(byte[] content, String name, MimeType mimeType) {
        this.content = content;
        this.name = name;
        this.mimeType = mimeType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MimeType getMimeType() {
        return mimeType;
    }

    public void setMimeType(MimeType mimeType) {
        this.mimeType = mimeType;
    }
}
