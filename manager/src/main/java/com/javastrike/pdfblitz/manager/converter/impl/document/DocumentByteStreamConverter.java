package com.javastrike.pdfblitz.manager.converter.impl.document;

import com.javastrike.pdfblitz.manager.converter.Converter;
import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.converter.management.IdentifierType;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.model.MimeType;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Handles conversion of a Document to and from java.io.ByteArrayInputStream
 *
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DocumentByteStreamConverter implements Converter<ByteArrayInputStream, Document> {


    @Override
    public boolean supports(Class<Object> clazz) {
        return ByteArrayInputStream.class.isAssignableFrom(clazz);
    }

    @Override
    public boolean handlesDocumentType(Class<? extends Document> documentType) {
        return Document.class.isAssignableFrom(documentType);
    }

    @Override
    public ByteArrayInputStream provideDocument(Document document, ConversionContext context) {
        return new ByteArrayInputStream(document.getContent());
    }


	@Override
	public Document convertToDocument(ByteArrayInputStream stream,
                                      ConversionContext context) throws ConversionException {

		Document document;
		try {
			String name = (String) context.getConversionParameter(IdentifierType.DOCUMENT_NAME).getValue();
			MimeType mimeType = (MimeType) context.getConversionParameter(IdentifierType.MIME_TYPE).getValue();
			byte[] content = IOUtils.toByteArray(stream);

            document = new Document(content,name,mimeType);
		} catch (IOException e) {
			throw new ConversionException("Error converting from java.io.ByteArrayInputStream to Document",e);
		}
		return document;
	}
}