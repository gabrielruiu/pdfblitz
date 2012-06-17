package com.javastrike.pdfblitz.manager.provider;

import com.javastrike.pdfblitz.manager.exception.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DocumentFileProvider implements DocumentProvider<File>{


    private Logger logger = LoggerFactory.getLogger(DocumentFileProvider.class);
    private File documentLocation;

    @Override
    public boolean supports(Class clazz) {
        return File.class.isAssignableFrom(clazz);
    }

    @Override
    public File provideDocument(Document document) throws ConversionException{

        if (documentLocation == null) {
            try {
                documentLocation = File.createTempFile("document-",null);
            } catch (IOException e) {
                logger.error("Error creating temporary file for document",e);
                throw new ConversionException("Error creating temporary file for document",e);
            }
        }
        return documentLocation;
    }
}
