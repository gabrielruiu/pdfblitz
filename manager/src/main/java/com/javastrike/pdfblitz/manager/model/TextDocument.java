package com.javastrike.pdfblitz.manager.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@XmlRootElement
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
