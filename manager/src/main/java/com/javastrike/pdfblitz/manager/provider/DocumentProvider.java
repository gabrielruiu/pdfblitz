package com.javastrike.pdfblitz.manager.provider;

import com.javastrike.pdfblitz.manager.exception.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;

/**
 * Provides a Document in various formats.
 * The output can limit the amount of information contained in a Document, eg. a ByteArrayInputStream can
 * only hold the document content, without the document title or MIME type
 *
 * @param <T> the type into which the document is converted
 *
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public interface DocumentProvider<T> {

    boolean supports(Class clazz);

    T provideDocument(Document document) throws ConversionException;
}
