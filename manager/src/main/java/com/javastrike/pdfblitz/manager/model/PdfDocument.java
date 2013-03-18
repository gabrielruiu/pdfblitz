package com.javastrike.pdfblitz.manager.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@XmlRootElement
public class PdfDocument extends Document {

    private String version;

    private boolean isEncrpyted;


    public PdfDocument() {
    }

    public PdfDocument(byte[] content, String name) {
        super(content, name, MimeType.PDF);
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

    @Override
    public MimeType getMimeType() {
        return MimeType.PDF;
    }

    @Override
    public void setMimeType(MimeType mimeType) {
    }
}
