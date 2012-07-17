package com.javastrike.pdfblitz.manager.converter;

import com.javastrike.pdfblitz.manager.exception.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.IOUtils;

/**
 * Handles conversion of a Document to and from java.io.ByteArrayInputStream
 *
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DocumentStreamProvider implements DocumentConverter<ByteArrayInputStream> {


    @Override
    public boolean supports(Class clazz) {
        return ByteArrayInputStream.class.isAssignableFrom(clazz);
    }

    @Override
    public ByteArrayInputStream provideDocument(Document document) {
        return new ByteArrayInputStream(document.getContent());
    }

	@Override
	public Document convertToDocument(ByteArrayInputStream stream, 
			AdditionalConversionData additionalData) throws ConversionException {
		
		Document document = new Document();
		try {
			String name = additionalData.get(AdditionalConversionData.NAME);
			String mimeType = additionalData.get(AdditionalConversionData.MIME_TYPE);
			byte[] content = IOUtils.toByteArray(stream);
			
			document.setName(name);
			document.setMIMEtype(mimeType);
			document.setContent(content);
		} catch (IOException e) {
			document = null;
			throw new ConversionException("Error converting from java.io.ByteArrayInputStream to Document",e);
		}
		return document;
	}
    
    
    
    
}