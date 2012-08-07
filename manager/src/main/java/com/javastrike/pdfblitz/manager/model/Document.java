package com.javastrike.pdfblitz.manager.model;

public class Document {


    private byte[] content;
    private String name;
    private String mimeType;

    public Document() {
    }

    public Document(byte[] content, String name, String mimeType) {
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

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
