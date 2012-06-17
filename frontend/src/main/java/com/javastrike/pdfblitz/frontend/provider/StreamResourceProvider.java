package com.javastrike.pdfblitz.frontend.provider;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.manager.exception.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.provider.DocumentProvider;
import com.vaadin.terminal.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class StreamResourceProvider implements DocumentProvider<StreamResource>{


    @Override
    public boolean supports(Class clazz) {
        return StreamResource.class.isAssignableFrom(clazz);
    }

    @Override
    public StreamResource provideDocument(final Document document) throws ConversionException {

        StreamResource.StreamSource streamSource = new StreamResource.StreamSource() {
            @Override
            public InputStream getStream() {
                return new ByteArrayInputStream(document.getContent());
            }
        };
        StreamResource streamResource =
                new StreamResource(streamSource, document.getName(), PdfBlitzApplication.getCurrentApplication());
        return streamResource;
    }
}
