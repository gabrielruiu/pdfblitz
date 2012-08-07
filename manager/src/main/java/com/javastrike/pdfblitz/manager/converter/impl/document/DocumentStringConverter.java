package com.javastrike.pdfblitz.manager.converter.impl.document;

import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.converter.Converter;
import com.javastrike.pdfblitz.manager.converter.management.IdentifierType;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;

/**
 * TODO: possible character set issues?
 *
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DocumentStringConverter implements Converter<String, Document> {

    @Override
    public boolean supports(Class<Object> clazz) {
        return String.class.isAssignableFrom(clazz);
    }

    @Override
    public boolean handlesDocumentType(Class<? extends Document> documentType) {
        return Document.class.isAssignableFrom(documentType);
    }

    @Override
    public String provideDocument(Document document, ConversionContext context) throws ConversionException {
        return new String(document.getContent());
    }

    @Override
    public Document convertToDocument(String string, ConversionContext context) throws ConversionException {

        byte[] content = string.getBytes();
        String name = (String) context.getConversionParameter(IdentifierType.DOCUMENT_NAME).getValue();
        String mimeType = (String) context.getConversionParameter(IdentifierType.MIME_TYPE).getValue();

        Document document = new Document(content,name,mimeType);

        return document;
    }
}
