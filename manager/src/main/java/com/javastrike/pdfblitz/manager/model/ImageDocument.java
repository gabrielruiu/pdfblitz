package com.javastrike.pdfblitz.manager.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@XmlRootElement
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
