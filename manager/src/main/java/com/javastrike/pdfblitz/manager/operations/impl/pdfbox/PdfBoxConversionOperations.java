package com.javastrike.pdfblitz.manager.operations.impl.pdfbox;

import com.javastrike.pdfblitz.manager.converter.impl.DefaultConversionContext;
import com.javastrike.pdfblitz.manager.converter.impl.IntegerArrayConversionParameter;
import com.javastrike.pdfblitz.manager.converter.impl.StringConversionParameter;
import com.javastrike.pdfblitz.manager.converter.impl.pdf.PdfToImageConverter;
import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.converter.management.ConverterResolver;
import com.javastrike.pdfblitz.manager.converter.management.IdentifierType;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.ImageDocument;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.javastrike.pdfblitz.manager.operations.ConversionOperations;
import com.javastrike.pdfblitz.manager.operations.ConversionSupport;
import com.javastrike.pdfblitz.manager.operations.impl.DefaultConversionSupport;
import org.apache.commons.collections.primitives.ArrayIntList;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Assert;

import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class PdfBoxConversionOperations implements ConversionOperations {


    private static final Logger LOG = Logger.getLogger(PdfBoxConversionOperations.class);

    private ConversionSupport conversionSupport;


    public PdfBoxConversionOperations() {
        this(new DefaultConversionSupport());
    }

    public PdfBoxConversionOperations(ConversionSupport conversionSupport) {

        Assert.assertNotNull("ConversionSupport must not be null", conversionSupport);
        this.conversionSupport = conversionSupport;
    }


    @Override
    public void setConverterResolver(ConverterResolver converterResolver) {
        this.conversionSupport.setConverterResolver(converterResolver);
    }

    @Override
    public ConverterResolver getConverterResolver() {
        return conversionSupport.getConverterResolver();
    }


    @Override
    public List<ImageDocument> convertDocumentToImages(PdfDocument pdfDocument) throws ConversionException {

        int[] pageIndices = generateIntegerArrayFromNumberInterval(1, getNumberOfPagesForPdf(pdfDocument));
        return convertDocumentToImages(pdfDocument, pageIndices);
    }

    @Override
    public List<ImageDocument> convertDocumentToImages(PdfDocument pdfDocument, int startIndex, int endIndex)
            throws ConversionException {

        int[] pageIndices =  generateIntegerArrayFromNumberInterval(startIndex,endIndex);
        return convertDocumentToImages(pdfDocument, pageIndices);
    }


    @Override
    public List<ImageDocument> convertDocumentToImages(PdfDocument pdfDocument, int... pageIndices)
            throws ConversionException {

        List<ImageDocument> images;
        try {

            PdfToImageConverter converter = (PdfToImageConverter) getConverterResolver()
                    .getConverter(PdfDocument.class, ImageDocument.class);

            ConversionContext context = new DefaultConversionContext()
                    .addConversionParameter(IdentifierType.DOCUMENT_NAME,
                            new StringConversionParameter(pdfDocument.getName()))
                    .addConversionParameter(IdentifierType.MIME_TYPE,
                            new StringConversionParameter("image/jpg"))
                    .addConversionParameter(IdentifierType.IMAGE_PAGE_INDICES,
                            new IntegerArrayConversionParameter(pageIndices))
                    .addConversionParameter(IdentifierType.IMAGE_FORMAT,
                            new StringConversionParameter("jpg"));

            images = converter.provideDocument(pdfDocument,context);

        } catch (ConversionException e) {
            LOG.error("Error converting PdfDocument into images",e);
            throw new ConversionException("Error converting PdfDocument into images",e);
        }
        return images;
    }

    @Override
    public PdfDocument convertImagesToPdfDocument(List<ImageDocument> images, ConversionContext context)
            throws ConversionException{

        PdfDocument pdfDocument;
        try {
            PdfToImageConverter converter = (PdfToImageConverter) getConverterResolver()
                    .getConverter(PdfDocument.class, ImageDocument.class);

            pdfDocument = converter.convertToDocument(images, context);

        } catch (ConversionException e){
            LOG.error("Error converting images into pdf",e);
            throw new ConversionException("Error converting images into pdf", e);
        }

        return pdfDocument;
    }


    private int[] generateIntegerArrayFromNumberInterval(int startIndex, int endIndex){

        ArrayIntList integers = new ArrayIntList();
        for (int i = startIndex; i<= endIndex; i++) {
            integers.add(i);
        }
        return integers.toArray();
    }

    private int getNumberOfPagesForPdf(PdfDocument pdfDocument) throws ConversionException {

        PDDocument pdDocument;
        try {
            pdDocument = convertPdfToPDD(pdfDocument);
        } catch (ConversionException e) {
            LOG.error("Error converting PdfDocument into PDDocument",e);
            throw new ConversionException("Error converting PdfDocument into PDDocument",e);
        }
        return pdDocument.getNumberOfPages();
    }

    private PdfDocument convertPDDToPdf(PDDocument pdDocument, String pdfDocumentName)
            throws ConversionException{

        ConversionContext context = new DefaultConversionContext()
                .addConversionParameter(IdentifierType.DOCUMENT_NAME, new StringConversionParameter(pdfDocumentName))
                .addConversionParameter(IdentifierType.MIME_TYPE, new StringConversionParameter("application/pdf"));

        return (PdfDocument) conversionSupport.getConverterResolver().getConverter(PdfDocument.class,PDDocument.class)
                .convertToDocument(pdDocument, context);
    }

    private PDDocument convertPdfToPDD(PdfDocument pdfDocument) throws ConversionException{

        return (PDDocument) conversionSupport.getConverterResolver().getConverter(PdfDocument.class, PDDocument.class)
                .provideDocument(pdfDocument, null);
    }

}
