package com.javastrike.pdfblitz.manager.converter;

import java.util.Map;

import com.javastrike.pdfblitz.manager.exception.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;

/**
 * Converts a Document to and from various types.
 * 
 * The output of the {@link #provideDocument(Document)} method can limit the amount of information contained in a Document,
 * eg. a ByteArrayInputStream can only hold the document content, without the document name or MIME type.
 *
 * The additionalData from the {@link convertDocument(T, AdditionalConversionData)} method serves the purpose
 * of keeping the data that the object T normally could not contain, and later use it to build a proper Document
 * object. For example, a java.io.File object cannot know the MIME type of the file.
 *
 * @param <T> the type into which the document is converted
 *
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public interface DocumentConverter<T> {
	
    boolean supports(Class clazz);

    T provideDocument(Document document) throws ConversionException;
    
    Document convertToDocument(T object, AdditionalConversionData additionalData) throws ConversionException;
}
