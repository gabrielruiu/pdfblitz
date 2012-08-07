package com.javastrike.pdfblitz.manager.model;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class PdfDocument extends Document {

    private String version;

    private boolean isEncrpyted;


    public PdfDocument() {
    }

    public PdfDocument(byte[] content, String name, String MIMEtype) {
        super(content, name, MIMEtype);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isEncrpyted() {
        return isEncrpyted;
    }

    public void setEncrpyted(boolean encrpyted) {
        isEncrpyted = encrpyted;
    }
}
