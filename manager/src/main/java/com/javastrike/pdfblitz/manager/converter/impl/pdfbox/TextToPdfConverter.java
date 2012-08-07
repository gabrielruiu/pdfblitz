package com.javastrike.pdfblitz.manager.converter.impl.pdfbox;

import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.converter.pdf.PdfConverter;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.javastrike.pdfblitz.manager.model.TextDocument;

import java.io.Reader;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
//TODO: finish
public class TextToPdfConverter implements PdfConverter<TextDocument>{


    @Override
    public boolean supports(Class<Object> clazz) {
        return TextDocument.class.isAssignableFrom(clazz);
    }

    @Override
    public boolean handlesDocumentType(Class<? extends Document> documentType) {
        return PdfDocument.class.isAssignableFrom(documentType);
    }

    @Override
    public TextDocument provideDocument(PdfDocument document, ConversionContext context) throws ConversionException {
        return null;
    }

    @Override
    public PdfDocument convertToDocument(TextDocument object, ConversionContext context) throws ConversionException {
        return null;
    }
}
