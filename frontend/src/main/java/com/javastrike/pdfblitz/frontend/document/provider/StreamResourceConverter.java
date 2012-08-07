package com.javastrike.pdfblitz.frontend.document.provider;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.manager.converter.Converter;
import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;
import com.vaadin.terminal.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class StreamResourceConverter implements Converter<StreamResource, Document> {


    @Override
    public boolean supports(Class clazz) {
        return StreamResource.class.isAssignableFrom(clazz);
    }

    @Override
    public boolean handlesDocumentType(Class<? extends Document> documentType) {
        return Document.class.isAssignableFrom(documentType);
    }

    @Override
    public StreamResource provideDocument(final Document document, ConversionContext context)
            throws ConversionException {

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

    @Override
    public Document convertToDocument(StreamResource streamResource, ConversionContext context)
            throws ConversionException {

        Document document;

        try {
            String name = streamResource.getFilename();
            String mimeType = streamResource.getMIMEType();
            byte[] content = IOUtils.toByteArray(streamResource.getStreamSource().getStream());

            document = new Document(content,name,mimeType);
        } catch (IOException e) {
            throw new ConversionException("Error converting from com.vaadin.terminal.StreamResource to Document",e);
        }
        return document;
    }
}
