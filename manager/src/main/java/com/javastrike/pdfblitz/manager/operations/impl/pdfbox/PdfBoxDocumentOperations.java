package com.javastrike.pdfblitz.manager.operations.impl.pdfbox;

import com.javastrike.pdfblitz.manager.converter.impl.IntegerArrayConversionParameter;
import com.javastrike.pdfblitz.manager.converter.impl.pdf.PdfToImageConverter;
import com.javastrike.pdfblitz.manager.exception.pdfoperations.PageIndicesOutOfRangeException;
import com.javastrike.pdfblitz.manager.exception.pdfoperations.PdfDocumentOperationException;
import com.javastrike.pdfblitz.manager.utils.DocumentNameUtils;
import com.javastrike.pdfblitz.manager.converter.impl.DefaultConversionContext;
import com.javastrike.pdfblitz.manager.converter.impl.StringConversionParameter;
import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.converter.management.ConverterResolver;
import com.javastrike.pdfblitz.manager.converter.management.IdentifierType;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.ImageDocument;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.javastrike.pdfblitz.manager.operations.ConversionSupport;
import com.javastrike.pdfblitz.manager.operations.PdfDocumentOperations;
import com.javastrike.pdfblitz.manager.operations.impl.DefaultConversionSupport;
import com.javastrike.pdfblitz.manager.utils.DocumentOperationUtils;
import org.apache.commons.collections.primitives.ArrayIntList;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class PdfBoxDocumentOperations  implements PdfDocumentOperations , ConversionSupport {


    private static final Logger LOG = Logger.getLogger(PdfBoxDocumentOperations.class);

    private ConversionSupport conversionSupport;


    public PdfBoxDocumentOperations() {
        this.conversionSupport = new DefaultConversionSupport();
    }

    public PdfBoxDocumentOperations(ConversionSupport conversionSupport) {

        Assert.assertNotNull("ConversionSupport must not be null",conversionSupport);
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



                                                /*  DOCUMENT OPERATIONS  */


    @Override
    public List<PdfDocument> extractPages(PdfDocument pdfDocument, int... pageIndices)
            throws PdfDocumentOperationException {

        List<PdfDocument> pdfDocuments = new ArrayList<PdfDocument>();
        try {
            // convert pdf to pdd
            PDDocument pdDocument =  (PDDocument) conversionSupport.getConverterResolver()
                    .getConverter(PdfDocument.class, PDDocument.class).provideDocument(pdfDocument, null);

            if (!DocumentOperationUtils.prepareAndCheckPageIndices(pdDocument.getNumberOfPages(), pageIndices)) {
                throw new PageIndicesOutOfRangeException("Page indices are out of range");
            }

            // get the pages from the pdd
            List<PDPage> pages = pdDocument.getDocumentCatalog().getAllPages();

            // for each page create a new pdd and add the page to it
            for (int pageNumber : pageIndices) {

                PDDocument inidividualPageDocument = new PDDocument();
                inidividualPageDocument.addPage(pages.get(pageNumber-1));

                // convert each newly created pdd into a pdf
                pdfDocuments.add(convertPDDToPdf(inidividualPageDocument,
                        DocumentNameUtils.addNumberPrefixToDocumentName(pdfDocument, pageNumber)));
            }


        } catch (ConversionException e) {
            LOG.error("Error extracting pages from PdfDocument",e);
            throw new PdfDocumentOperationException("Error extracting pages from PdfDocument",e);
        } catch (IOException e) {
            LOG.error("Error extracting pages from PdfDocument",e);
            throw new PdfDocumentOperationException("Error extracting pages from PdfDocument",e);
        }

        return pdfDocuments;
    }

    @Override
    public PdfDocument deletePages(PdfDocument originalPdfDocument, int... pageIndices)
            throws PdfDocumentOperationException {

        if( originalPdfDocument.isEncrpyted() ) {
            throw new PdfDocumentOperationException("Cannot operate on encrypted documents");
        }


        PdfDocument modifiedPdfDocument;

        try {
            PDDocument pdDocument = convertPdfToPDD(originalPdfDocument);

            if (!DocumentOperationUtils.prepareAndCheckPageIndices(pdDocument.getNumberOfPages(), pageIndices)) {
                throw new PageIndicesOutOfRangeException("Page indices are out of range");
            }

            removePagesFromPDDocument(pdDocument,pageIndices);

            modifiedPdfDocument = convertPDDToPdf(pdDocument,originalPdfDocument.getName());

        } catch (ConversionException e) {
            throw new PdfDocumentOperationException("Error converting PDDocument to PdfDocument",e);
        }

        return modifiedPdfDocument;
    }

    @Override
    public PdfDocument mergeDocuments(ConversionContext conversionContext, PdfDocument... pdfDocuments)
            throws PdfDocumentOperationException {

        PdfDocument pdfDocument;
        try {
            PDFMergerUtility merger = new PDFMergerUtility();
            ByteArrayOutputStream mergedContent = new ByteArrayOutputStream();
            for (PdfDocument mergingDocument : pdfDocuments) {
                merger.addSource(new ByteArrayInputStream(mergingDocument.getContent()));
            }
            merger.setDestinationStream(mergedContent);
            merger.mergeDocuments();

            byte[] content = mergedContent.toByteArray();
            String name = (String) conversionContext.getConversionParameter(IdentifierType.DOCUMENT_NAME).getValue();
            String mimeType = (String) conversionContext.getConversionParameter(IdentifierType.MIME_TYPE).getValue();

            pdfDocument = new PdfDocument(content,name,mimeType);

        } catch (IOException e) {
            throw new PdfDocumentOperationException("Error merging documents", e);
        } catch (COSVisitorException e) {
            throw new PdfDocumentOperationException("Error merging documents", e);
        }
        return pdfDocument;
    }

    @Override
    public List<PdfDocument> splitAtPages(PdfDocument pdfDocument, int... pageIndices)
            throws PdfDocumentOperationException {

        List<PdfDocument> pdfDocuments = new ArrayList<PdfDocument>();
        try {
            //convert pdf to pdd
            PDDocument pdDocument = convertPdfToPDD(pdfDocument);

            //prepare and check pageIndices
            if (!DocumentOperationUtils.prepareAndCheckPageIndices(pdDocument.getNumberOfPages(), pageIndices)) {
                throw new PageIndicesOutOfRangeException("Page indices are out of range");
            }

            //iterate through the documents pages, and split at each of the indices
            //turn documents back to pdfs
            PDDocument pdDocumentSplit = new PDDocument();
            List<PDPage> documentPages = pdDocument.getDocumentCatalog().getAllPages();
            for (int pageNumber = 1; pageNumber <=documentPages.size(); pageNumber++) {

                pdDocumentSplit.addPage(documentPages.get(pageNumber-1));
                if (splitAtThisPage(pageNumber, pageIndices) || pageNumber == documentPages.size()) {
                    PdfDocument pdfDocumentSplit = convertPDDToPdf(pdDocumentSplit,
                            DocumentNameUtils.addNumberPrefixToDocumentName(pdfDocument,pageNumber));
                    pdfDocuments.add(pdfDocumentSplit);
                    pdDocumentSplit = new PDDocument();
                }
            }

        } catch (ConversionException e){
            LOG.error("Error splitting pages from PdfDocument",e);
            throw new PdfDocumentOperationException("Error splitting pages from PdfDocument",e);
        } catch (IOException e) {
            LOG.error("Error splitting pages from PdfDocument",e);
            throw new PdfDocumentOperationException("Error splitting pages from PdfDocument",e);
        }

        return pdfDocuments;
    }

    @Override
    public PdfDocument encrypt(PdfDocument pdfDocument) throws PdfDocumentOperationException {
        return pdfDocument;
    }

    @Override
    public PdfDocument decrypt(PdfDocument pdfDocument) throws PdfDocumentOperationException {
        return pdfDocument;
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


    // Every time the PDDocument removes a page, the remaining indices no longer correspond to
    // the original page numbers of the original PDDocument, so every time a page is removed,
    // they (the page indices) must be updated by decreasing their value by one
    private void removePagesFromPDDocument(PDDocument pdDocument, int... pageIndices){

        if (pageIndices.length != 0){
            //0-based indexing when removing pages from a PDDocument
            pdDocument.removePage(pageIndices[0]-1);
            removePagesFromPDDocument(pdDocument, updatePageIndicesForDeletion(pageIndices));
        } else {
            return;
        }
    }

    private int[] updatePageIndicesForDeletion(int... pageIndices){

        ArrayIntList newPageIndices = new ArrayIntList();
        for (int i=0; i<pageIndices.length - 1;i++){
            //shift to the left and decrease the value by 1
            newPageIndices.add(i, pageIndices[i+1]-1);
        }
        return newPageIndices.toArray();
    }

    private boolean splitAtThisPage(int currentPage, int... splitPages) {

        for (int splitPage : splitPages) {
            if (currentPage == splitPage) {
                return true;
            }
        }
        return false;
    }
}
