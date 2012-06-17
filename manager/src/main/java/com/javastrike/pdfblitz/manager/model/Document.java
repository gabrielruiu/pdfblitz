package com.javastrike.pdfblitz.manager.model;

public class Document {


    private byte[] content;
    private String name;
    private String MIMEtype;

    public Document() {
    }

    public Document(byte[] content, String name, String MIMEtype) {
        this.content = content;
        this.name = name;
        this.MIMEtype = MIMEtype;
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

    public String getMIMEtype() {
        return MIMEtype;
    }

    public void setMIMEtype(String MIMEtype) {
        this.MIMEtype = MIMEtype;
    }
}
