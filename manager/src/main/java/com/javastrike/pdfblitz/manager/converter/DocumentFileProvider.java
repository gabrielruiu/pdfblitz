package com.javastrike.pdfblitz.manager.converter;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.javastrike.pdfblitz.manager.exception.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;

/**
 * Handles conversion of a Document to and from java.io.File
 *
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DocumentFileProvider implements DocumentConverter<File> {


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

	@Override
	public Document convertToDocument(File file, AdditionalConversionData additionalData) throws ConversionException {
		
		Document document = new Document();
		try {
			String name = file.getName();
			String mimeType = additionalData.get(AdditionalConversionData.MIME_TYPE);
			byte[] content = FileUtils.readFileToByteArray(file);
			
			document.setName(name);
			document.setMIMEtype(mimeType);
			document.setContent(content);
		} catch (IOException e) {
			document = null;
			throw new ConversionException("Error convertion java.io.File to Document",e);
		}
		return document;
	}
    
    
}
