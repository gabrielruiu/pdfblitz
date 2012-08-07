package com.javastrike.pdfblitz.manager.converter;

import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public interface Converter<T, D extends Document> {

    boolean supports(Class<Object> clazz);

    boolean handlesDocumentType(Class<? extends Document> documentType);

    T provideDocument(D document, ConversionContext context) throws ConversionException;

    D convertToDocument(T object, ConversionContext context) throws ConversionException;
}
