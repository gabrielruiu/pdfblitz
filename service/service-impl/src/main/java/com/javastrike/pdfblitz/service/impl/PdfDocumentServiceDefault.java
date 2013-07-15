package com.javastrike.pdfblitz.service.impl;

import com.javastrike.pdfblitz.manager.DocumentManager;
import com.javastrike.pdfblitz.manager.converter.management.IdentifierType;
import com.javastrike.pdfblitz.manager.exception.pdfoperations.PdfDocumentOperationException;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.javastrike.pdfblitz.service.api.PdfDocumentService;
import com.javastrike.pdfblitz.service.api.dto.DocumentDTO;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.javastrike.pdfblitz.service.api.paths.PdfPaths.*;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@1and1.ro)
 */
@Path(PDF_SERVICE_ROOT)
@Produces({MediaType.APPLICATION_XML})
public class PdfDocumentServiceDefault implements PdfDocumentService {

    private DocumentManager documentManager;

    public PdfDocumentServiceDefault() {
        this.documentManager = new DocumentManager();
    }

    @POST
    @Path(EXTRACT_PAGES)
    @Override
    public List<PdfDocument> extractPages(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException {

        PdfDocument pdfDocument = documentDTO.getDocuments().get(0);
        int[] pageIndices = (int[]) documentDTO.getConversionContext().getConversionParameter(IdentifierType.PAGE_INDICES).getValue();
        return documentManager.getDocumentOperations().extractPages(pdfDocument, pageIndices);
    }

    @POST
    @Path(DELETE_PAGES)
    @Override
    public PdfDocument deletePages(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException {

        PdfDocument pdfDocument = documentDTO.getDocuments().get(0);
        int[] pageIndices = (int[]) documentDTO.getConversionContext().getConversionParameter(IdentifierType.PAGE_INDICES).getValue();
        return documentManager.getDocumentOperations().deletePages(pdfDocument, pageIndices);
    }

    @POST
    @Path(MERGE_DOCUMENTS)
    @Override
    public PdfDocument mergeDocuments(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException {

        PdfDocument[] pdfDocuments = documentDTO.getDocuments().toArray(new PdfDocument[0]);
        return documentManager.getDocumentOperations().mergeDocuments(documentDTO.getConversionContext(), pdfDocuments);
    }

    @POST
    @Path(SPLIT_AT_PAGES)
    @Override
    public List<PdfDocument> splitAtPages(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException {

        PdfDocument pdfDocument = documentDTO.getDocuments().get(0);
        int[] pageIndices = (int[]) documentDTO.getConversionContext().getConversionParameter(IdentifierType.PAGE_INDICES).getValue();
        return documentManager.getDocumentOperations().splitAtPages(pdfDocument, pageIndices);
    }

    @POST
    @Path(ENCRYPT)
    @Override
    public PdfDocument encrypt(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException {
        return null;
    }

    @POST
    @Path(DECRYPT)
    @Override
    public PdfDocument decrypt(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException {
        return null;
    }
}
