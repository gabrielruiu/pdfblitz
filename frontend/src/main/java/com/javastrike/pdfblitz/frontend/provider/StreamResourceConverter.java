package com.javastrike.pdfblitz.frontend.provider;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.manager.converter.AdditionalConversionData;
import com.javastrike.pdfblitz.manager.converter.DocumentConverter;
import com.javastrike.pdfblitz.manager.exception.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;
import com.vaadin.terminal.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class StreamResourceConverter implements DocumentConverter<StreamResource> {


    @Override
    public boolean supports(Class clazz) {
        return StreamResource.class.isAssignableFrom(clazz);
    }

    @Override
    public StreamResource provideDocument(final Document document) throws ConversionException {

        StreamResource.StreamSource streamSource = new StreamResource.StreamSource() {
            @Override
            public InputStream getStream() {
                return new ByteArrayInputStream(document.getContent());
            }
        };
        StreamResource streamResource =
                new StreamResource(streamSource, document.getName(), PdfBlitzApplication.getCurrentApplication());
        return streamResource;
    }

	@Override
	public Document convertToDocument(StreamResource streamResource,
			AdditionalConversionData additionalData) throws ConversionException {
		
		Document document = new Document();

		try {
			String name = streamResource.getFilename();
			String mimeType = streamResource.getMIMEType();
			byte[] content = IOUtils.toByteArray(streamResource.getStreamSource().getStream());
			
			document.setName(name);
			document.setMIMEtype(mimeType);
			document.setContent(content);
		} catch (IOException e) {
			document = null;
			throw new ConversionException("Error converting from com.vaadin.terminal.StreamResource to Document",e);
		}
		return document;
	}
    
    
}
