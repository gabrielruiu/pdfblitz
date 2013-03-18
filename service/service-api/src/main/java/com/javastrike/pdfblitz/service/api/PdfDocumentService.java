package com.javastrike.pdfblitz.service.api;

import com.javastrike.pdfblitz.manager.exception.pdfoperations.PdfDocumentOperationException;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.javastrike.pdfblitz.service.api.dto.DocumentDTO;

import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@1and1.ro)
 */
public interface PdfDocumentService {

    /*********  Page related operation  ************/

    List<PdfDocument> extractPages(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException;

    PdfDocument deletePages(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException;


    /*********  Document related operations  *******/

    PdfDocument mergeDocuments(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException;

    List<PdfDocument> splitAtPages(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException;

    PdfDocument encrypt(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException;

    PdfDocument decrypt(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException;
}
