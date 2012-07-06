package com.javastrike.pdfblitz.manager.converter;

import com.javastrike.pdfblitz.manager.exception.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DocumentFileProvider implements DocumentProvider<File> {


    private Logger logger = Logger.getLogger(DocumentFileProvider.class);
    private File documentLocation;

    @Override
    public boolean supports(Class clazz) {
        return File.class.isAssignableFrom(clazz);
    }

    @Override
    public File provideDocument(Document document) throws ConversionException{

        documentLocation = null;
        try {
            documentLocation = File.createTempFile("document-",null);
        } catch (IOException e) {
            logger.error("Error creating temporary file for document",e);
            throw new ConversionException("Error creating temporary file for document",e);
        }
        return documentLocation;
    }
}
