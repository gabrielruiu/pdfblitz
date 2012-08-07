package com.javastrike.pdfblitz.manager.converter.impl.document;

import java.io.File;
import java.io.IOException;

import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.converter.Converter;
import com.javastrike.pdfblitz.manager.converter.management.IdentifierType;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;

/**
 * Handles conversion of a Document to and from java.io.File
 *
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DocumentFileConverter implements Converter<File, Document> {


    private Logger logger = Logger.getLogger(DocumentFileConverter.class);
    private File documentLocation;

    @Override
    public boolean supports(Class<Object> clazz) {
        return File.class.isAssignableFrom(clazz);
    }

    @Override
    public boolean handlesDocumentType(Class<? extends Document> documentType) {
        return Document.class.isAssignableFrom(documentType);
    }

    @Override
    public File provideDocument(Document document, ConversionContext context) throws ConversionException{

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
	public Document convertToDocument(File file, ConversionContext context) throws ConversionException {

		Document document;
		try {
			String name = file.getName();
			String mimeType = (String) context.getConversionParameter(IdentifierType.MIME_TYPE).getValue();
			byte[] content = FileUtils.readFileToByteArray(file);

            document = new Document(content,name,mimeType);
		} catch (IOException e) {
			throw new ConversionException("Error conversion java.io.File to Document",e);
		}
		return document;
	}
}
