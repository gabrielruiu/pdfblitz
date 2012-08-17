package com.javastrike.pdfblitz.manager.converter.impl.pdfbox;

import com.javastrike.pdfblitz.manager.converter.impl.DefaultConversionContext;
import com.javastrike.pdfblitz.manager.converter.impl.StringConversionParameter;
import com.javastrike.pdfblitz.manager.converter.impl.document.DocumentStringConverter;
import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.converter.management.IdentifierType;
import com.javastrike.pdfblitz.manager.converter.pdf.PdfConverter;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.javastrike.pdfblitz.manager.model.TextDocument;
import org.apache.log4j.Logger;
import org.apache.pdfbox.TextToPDF;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class TextToPdfConverter implements PdfConverter<TextDocument>{


    private static final Logger LOG = Logger.getLogger(TextToPdfConverter.class);

    @Override
    public boolean supports(Class<Object> clazz) {
        return TextDocument.class.isAssignableFrom(clazz);
    }

    @Override
    public boolean handlesDocumentType(Class<? extends Document> documentType) {
        return PdfDocument.class.isAssignableFrom(documentType);
    }

    @Override
    public TextDocument provideDocument(PdfDocument pdfDocument, ConversionContext context) throws ConversionException {
        return null;
    }

    @Override
    public PdfDocument convertToDocument(TextDocument textDocument, ConversionContext context) throws ConversionException {

        PdfDocument pdfDocument;
        try {

            TextToPDF textToPDF = new TextToPDF();
            DocumentStringConverter documentStringConverter = new DocumentStringConverter();

            String documentContentAsString = documentStringConverter.provideDocument(textDocument, null);
            PDDocument pdDocument = textToPDF.createPDFFromText(new StringReader(documentContentAsString));

            PdfToPDDConverter pdfToPDDConverter = new PdfToPDDConverter();
            ConversionContext pdfToPDDConversionContext = new DefaultConversionContext()
                    .addConversionParameter(IdentifierType.DOCUMENT_NAME,
                            new StringConversionParameter(replaceTextExtensionWithPdf(textDocument.getName())))
                    .addConversionParameter(IdentifierType.MIME_TYPE,
                            new StringConversionParameter("application/pdf"));

            pdfDocument = pdfToPDDConverter.convertToDocument(pdDocument, pdfToPDDConversionContext);

        } catch (IOException e) {
            LOG.error("Error converting TextDocument into PdfDocument", e);
            throw new ConversionException("Error converting TextDocument into PdfDocument", e);
        }
        return pdfDocument;
    }


    private String replaceTextExtensionWithPdf(String documentName) {

        String[] parts = documentName.split("\\.");
        return parts[0] + ".pdf";
    }
}
