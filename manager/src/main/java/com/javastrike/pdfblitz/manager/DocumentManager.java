package com.javastrike.pdfblitz.manager;

import com.javastrike.pdfblitz.manager.operations.ConversionOperations;
import com.javastrike.pdfblitz.manager.operations.PdfDocumentOperations;
import com.javastrike.pdfblitz.manager.operations.impl.pdfbox.PdfBoxConversionOperations;
import com.javastrike.pdfblitz.manager.operations.impl.pdfbox.PdfBoxDocumentOperations;

//TODO: UNDO functionality?
public class DocumentManager {


    private PdfDocumentOperations documentOperations;
    private ConversionOperations conversionOperations;


    public DocumentManager() {
        this(new PdfBoxDocumentOperations(), new PdfBoxConversionOperations());

    }

    public DocumentManager(PdfDocumentOperations documentOperations, ConversionOperations conversionOperations) {

        this.documentOperations = documentOperations;
        this.conversionOperations = conversionOperations;

    }



    public PdfDocumentOperations getDocumentOperations() {
        return documentOperations;
    }

    public void setDocumentOperations(PdfDocumentOperations documentOperations) {
        this.documentOperations = documentOperations;
    }


    public ConversionOperations getConversionOperations() {
        return conversionOperations;
    }

    public void setConversionOperations(ConversionOperations conversionOperations) {
        this.conversionOperations = conversionOperations;
    }
}
