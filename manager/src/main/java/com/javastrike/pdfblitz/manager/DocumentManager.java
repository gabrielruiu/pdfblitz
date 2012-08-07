package com.javastrike.pdfblitz.manager;

import com.javastrike.pdfblitz.manager.converter.management.ConverterResolver;
import com.javastrike.pdfblitz.manager.operations.ConversionOperations;
import com.javastrike.pdfblitz.manager.operations.ConversionSupport;
import com.javastrike.pdfblitz.manager.operations.DocumentOperations;
import com.javastrike.pdfblitz.manager.operations.impl.DefaultConversionSupport;
import com.javastrike.pdfblitz.manager.operations.impl.pdfbox.PdfBoxConversionOperations;
import com.javastrike.pdfblitz.manager.operations.impl.pdfbox.PdfBoxDocumentOperations;

//TODO: UNDO functionality?
public class DocumentManager {


    private DocumentOperations documentOperations;
    private ConversionOperations conversionOperations;


    public DocumentManager() {
        this(new PdfBoxDocumentOperations(), new PdfBoxConversionOperations());

    }

    public DocumentManager(DocumentOperations documentOperations, ConversionOperations conversionOperations) {

        this.documentOperations = documentOperations;
        this.conversionOperations = conversionOperations;

    }



    public DocumentOperations getDocumentOperations() {
        return documentOperations;
    }

    public void setDocumentOperations(DocumentOperations documentOperations) {
        this.documentOperations = documentOperations;
    }


    public ConversionOperations getConversionOperations() {
        return conversionOperations;
    }

    public void setConversionOperations(ConversionOperations conversionOperations) {
        this.conversionOperations = conversionOperations;
    }
}
