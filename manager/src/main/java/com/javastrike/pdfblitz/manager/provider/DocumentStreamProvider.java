package com.javastrike.pdfblitz.manager.provider;

import com.javastrike.pdfblitz.manager.model.Document;

import java.io.ByteArrayInputStream;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DocumentStreamProvider implements DocumentProvider<ByteArrayInputStream> {


    @Override
    public boolean supports(Class clazz) {
        return ByteArrayInputStream.class.isAssignableFrom(clazz);
    }

    @Override
    public ByteArrayInputStream provideDocument(Document document) {
        return new ByteArrayInputStream(document.getContent());
    }
}
