package com.javastrike.pdfblitz.service.client;

import com.javastrike.pdfblitz.manager.exception.pdfoperations.PdfDocumentOperationException;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.javastrike.pdfblitz.service.api.PdfDocumentService;
import com.javastrike.pdfblitz.service.api.dto.DocumentDTO;
import com.javastrike.pdfblitz.service.api.paths.PdfPaths;

import java.util.List;

import static com.javastrike.pdfblitz.service.api.paths.PdfPaths.PDF_SERVICE_ROOT;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class PdfServiceClient extends AbstractGateway implements PdfDocumentService {


    public PdfServiceClient(String baseURL) {
        super(baseURL);
    }

    @Override
    public List<PdfDocument> extractPages(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException {
        return post(PDF_SERVICE_ROOT.concat(PdfPaths.EXTRACT_PAGES), documentDTO, List.class);
    }

    @Override
    public PdfDocument deletePages(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException {
        return post(PDF_SERVICE_ROOT.concat(PdfPaths.DELETE_PAGES), documentDTO, PdfDocument.class);
    }

    @Override
    public PdfDocument mergeDocuments(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException {
        return post(PDF_SERVICE_ROOT.concat(PdfPaths.MERGE_DOCUMENTS), documentDTO, PdfDocument.class);
    }

    @Override
    public List<PdfDocument> splitAtPages(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException {
        return post(PDF_SERVICE_ROOT.concat(PdfPaths.SPLIT_AT_PAGES), documentDTO, List.class);
    }

    @Override
    public PdfDocument encrypt(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException {
        return null;
    }

    @Override
    public PdfDocument decrypt(DocumentDTO<PdfDocument> documentDTO) throws PdfDocumentOperationException {
        return null;
    }

    @Override
    public <T> T post(String resourceUrl, Object requestBody, Class<T> returnType) throws PdfDocumentOperationException {
        try{
            return super.post(resourceUrl, requestBody, returnType);
        } catch (Exception e) {
            throw new PdfDocumentOperationException(e);
        }
    }
}
