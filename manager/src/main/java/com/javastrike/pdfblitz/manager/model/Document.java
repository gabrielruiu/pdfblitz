package com.javastrike.pdfblitz.manager.model;

import java.io.File;

public class Document {

    private byte[] content;
    private String name;
    private String MIMEtype;
    private File documentLocation;

    public Document() {
    }

    public Document(byte[] content, String name, String MIMEtype) {
        this.content = content;
        this.name = name;
        this.MIMEtype = MIMEtype;
        this.documentLocation = null;
    }

    public Document(byte[] content, String name, String MIMEtype, File documentLocation) {
        this.content = content;
        this.name = name;
        this.MIMEtype = MIMEtype;
        this.documentLocation = documentLocation;
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

    public File getDocumentLocation() {
        return documentLocation;
    }

    public void setDocumentLocation(File documentLocation) {
        this.documentLocation = documentLocation;
    }
}
