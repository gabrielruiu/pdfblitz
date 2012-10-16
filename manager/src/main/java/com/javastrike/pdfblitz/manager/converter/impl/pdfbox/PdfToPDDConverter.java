package com.javastrike.pdfblitz.manager.converter.impl.pdfbox;

import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.converter.management.IdentifierType;
import com.javastrike.pdfblitz.manager.converter.pdf.PdfConverter;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class PdfToPDDConverter implements PdfConverter<PDDocument> {


    @Override
    public boolean supports(Class<Object> clazz) {
        return PDDocument.class.isAssignableFrom(clazz);
    }

    @Override
    public boolean handlesDocumentType(Class<? extends Document> documentType) {
        return PdfDocument.class.isAssignableFrom(documentType);
    }

    @Override
    public PDDocument provideDocument(PdfDocument pdfDocument, ConversionContext context) throws ConversionException {

        PDDocument pdDocument;
        try {
            pdDocument = PDDocument.load(new ByteArrayInputStream(pdfDocument.getContent()));
        } catch (IOException e) {
            throw new ConversionException("Error converting Document to PDDocument",e);
        }
        return pdDocument;
    }

    @Override
    public PdfDocument convertToDocument(PDDocument pdDocument, ConversionContext context)
            throws ConversionException {

        PdfDocument document;
        try {
            byte[] content = getContentFromPDDocument(pdDocument);
            String name = (String) context.getConversionParameter(IdentifierType.DOCUMENT_NAME).getValue();

            document = new PdfDocument(content,name);
        } catch (IOException e) {
            throw new ConversionException("Error converting from PDDocument to Document",e);
        } catch (COSVisitorException e) {
            throw new ConversionException("Error converting from PDDocument to Document",e);
        }
        return document;
    }


    private byte[] getContentFromPDDocument(PDDocument pdDocument) throws IOException, COSVisitorException{

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        pdDocument.save(stream);
        return stream.toByteArray();
    }
}
