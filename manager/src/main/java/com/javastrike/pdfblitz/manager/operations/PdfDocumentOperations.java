package com.javastrike.pdfblitz.manager.operations;

import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.exception.pdfoperations.PdfDocumentOperationException;
import com.javastrike.pdfblitz.manager.model.PdfDocument;

import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public interface PdfDocumentOperations extends DocumentOperations {

    /*********  Page related operation  ************/

    List<PdfDocument> extractPages(PdfDocument pdfDocument, int... pageIndices) throws PdfDocumentOperationException;

    PdfDocument deletePages(PdfDocument pdfDocument, int... pageIndices) throws PdfDocumentOperationException;


    /*********  Document related operations  *******/

    PdfDocument mergeDocuments(ConversionContext conversionContext, PdfDocument... documents)
            throws PdfDocumentOperationException;

    List<PdfDocument> splitAtPages(PdfDocument pdfDocument, int... pageInidices) throws PdfDocumentOperationException;

    PdfDocument encrypt(PdfDocument document) throws PdfDocumentOperationException;

    PdfDocument decrypt(PdfDocument document) throws PdfDocumentOperationException;
}
