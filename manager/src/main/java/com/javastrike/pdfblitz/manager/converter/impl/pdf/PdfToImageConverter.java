package com.javastrike.pdfblitz.manager.converter.impl.pdf;

import com.javastrike.pdfblitz.manager.converter.impl.pdfbox.ImageToPDDConverter;
import com.javastrike.pdfblitz.manager.converter.impl.pdfbox.PdfToPDDConverter;
import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.converter.management.IdentifierType;
import com.javastrike.pdfblitz.manager.converter.management.impl.DefaultConversionContext;
import com.javastrike.pdfblitz.manager.converter.management.impl.IntegerConversionParameter;
import com.javastrike.pdfblitz.manager.converter.management.impl.StringConversionParameter;
import com.javastrike.pdfblitz.manager.converter.pdf.PdfConverter;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.model.ImageDocument;
import com.javastrike.pdfblitz.manager.model.MimeType;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Converts several {@link ImageDocument} files to and from a single {@link PdfDocument}
 *
 * All it does is use {@link com.javastrike.pdfblitz.manager.converter.impl.pdfbox.PdfToPDDConverter}
 * and {@link com.javastrike.pdfblitz.manager.converter.impl.pdfbox.ImageToPDDConverter} to switch
 * between {@link ImageDocument} and {@link PdfDocument}
 *
 *
 * @see com.javastrike.pdfblitz.manager.converter.impl.pdfbox.PdfToPDDConverter
 * @see com.javastrike.pdfblitz.manager.converter.impl.pdfbox.ImageToPDDConverter
 *
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class PdfToImageConverter implements PdfConverter<List<ImageDocument>> {


    private static final Logger LOG = Logger.getLogger(PdfToImageConverter.class);

    @Override
    public boolean supports(Class<Object> clazz) {
        return ImageDocument.class.isAssignableFrom(clazz);
    }

    @Override
    public boolean handlesDocumentType(Class<? extends Document> documentType) {
        return PdfDocument.class.isAssignableFrom(documentType);
    }

    @Override
    public List<ImageDocument> provideDocument(PdfDocument pdfDocument, ConversionContext context)
            throws ConversionException {

        List<ImageDocument> imageDocuments;

        PDDocument pdDocument = convertPdfToPDD(pdfDocument, context);
        imageDocuments = convertPagesIntoImage(pdDocument, context);

        return imageDocuments;
    }

    @Override
    public PdfDocument convertToDocument(List<ImageDocument> images, ConversionContext context)
            throws ConversionException {

        PdfDocument pdfDocument;

        try {
            PDDocument pdDocument = new PDDocument();
            for (ImageDocument image : images) {
                PDPage page = new PDPage();

                pdDocument.addPage(page);
                ByteArrayInputStream bis = new ByteArrayInputStream(image.getContent());
                PDXObjectImage xImage = new PDJpeg(pdDocument, bis);
                IOUtils.closeQuietly(bis);

                PDPageContentStream contentStream = new PDPageContentStream(pdDocument, page);
                contentStream.drawImage( xImage, 0, 0 );
                contentStream.close();
            }

            pdfDocument = convertPDDToPdf(pdDocument,context);

        } catch (ConversionException e) {
            LOG.error("Error converting images into a pdf", e);
            throw new ConversionException("Error converting images into a pdf", e);
        } catch (IOException e) {
            LOG.error("Error converting images into a pdf", e);
            throw new ConversionException("Error converting images into a pdf", e);
        }

        return pdfDocument;
    }

    //TODO: replace with DocumentNameUtils.addNumberPrefixToDocumentName()
    private String generateOutputForImage(String outputPrefix, int pageNumber) {
        return MessageFormat.format("{0}_{1}.{2}",outputPrefix,pageNumber, MimeType.IMAGE_JPEG.getFileExtension());
    }

    private PDDocument convertPdfToPDD(PdfDocument pdfDocument, ConversionContext context) throws ConversionException {

        ConversionContext pddConversionContext = new DefaultConversionContext()
                .addConversionParameter(IdentifierType.DOCUMENT_NAME,
                        context.getConversionParameter(IdentifierType.DOCUMENT_NAME))
                .addConversionParameter(IdentifierType.MIME_TYPE,
                        context.getConversionParameter(IdentifierType.MIME_TYPE));

        PDDocument pdDocument = new PdfToPDDConverter().provideDocument(pdfDocument, pddConversionContext);

        return pdDocument;
    }

    private PdfDocument convertPDDToPdf(PDDocument pdDocument, ConversionContext context) throws ConversionException{

        PdfDocument pdfDocument = new PdfToPDDConverter().convertToDocument(pdDocument,context);
        return pdfDocument;
    }

    private List<ImageDocument> convertPagesIntoImage(PDDocument pdDocument, ConversionContext context)
            throws ConversionException {

        List<ImageDocument> imageDocuments = new ArrayList<ImageDocument>();

        int[] pageRange = (int[]) context.getConversionParameter(IdentifierType.IMAGE_PAGE_INDICES).getValue();

        String documentName = (String) context.getConversionParameter(IdentifierType.DOCUMENT_NAME).getValue();
        context.addConversionParameter(IdentifierType.IMAGE_OUTPUT_PREFIX, new StringConversionParameter(documentName));

        for (int pageNumber : pageRange) {

            ImageDocument imageDocument = convertIndividualPageToImage(pdDocument, context, pageNumber);
            imageDocuments.add(imageDocument);
        }
        return imageDocuments;
    }

    private ImageDocument convertIndividualPageToImage(PDDocument pdDocument, ConversionContext context, int currentPage)
            throws ConversionException{

        ImageToPDDConverter imageToPDDConverter = new ImageToPDDConverter();
        ConversionContext imageConversionContext = generateImageConversionContext(context, currentPage);
        ImageDocument imageDocument = imageToPDDConverter.convertToDocument(pdDocument,imageConversionContext);
        return imageDocument;
    }

    //builds the ConversionContext for a specific page in the document
    private ConversionContext generateImageConversionContext(ConversionContext context, int pageNumber) {

        String imageNamePrefix = (String) context.getConversionParameter(IdentifierType.IMAGE_OUTPUT_PREFIX).getValue();
        String imageName = generateOutputForImage(imageNamePrefix,pageNumber);

        ConversionContext imageConversionContext = new DefaultConversionContext()
                .addConversionParameter(IdentifierType.DOCUMENT_NAME,
                        new StringConversionParameter(imageName))
                .addConversionParameter(IdentifierType.MIME_TYPE,
                        context.getConversionParameter(IdentifierType.MIME_TYPE))
                .addConversionParameter(IdentifierType.IMAGE_PAGE_NUMBER,
                        new IntegerConversionParameter(pageNumber));

        return imageConversionContext;
    }
}
