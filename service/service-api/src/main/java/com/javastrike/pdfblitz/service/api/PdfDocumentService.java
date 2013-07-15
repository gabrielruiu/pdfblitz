package com.javastrike.pdfblitz.service.api;

import com.javastrike.pdfblitz.manager.exception.pdfoperations.PdfDocumentOperationException;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.javastrike.pdfblitz.service.api.dto.DocumentDTO;

import javax.ws.rs.Consumes;
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
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public interface PdfDocumentService {

    /*********  Page related operation  ************/
    @POST
    @Path(EXTRACT_PAGES)
    List<PdfDocument> extractPages(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException;

    @POST
    @Path(DELETE_PAGES)
    PdfDocument deletePages(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException;


    /*********  Document related operations  *******/

    @POST
    @Path(MERGE_DOCUMENTS)
    PdfDocument mergeDocuments(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException;

    @POST
    @Path(SPLIT_AT_PAGES)
    List<PdfDocument> splitAtPages(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException;

    @POST
    @Path(ENCRYPT)
    PdfDocument encrypt(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException;

    @POST
    @Path(DECRYPT)
    PdfDocument decrypt(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException;
}
