package com.javastrike.pdfblitz.service.impl;

import com.javastrike.pdfblitz.manager.DocumentManager;
import com.javastrike.pdfblitz.manager.converter.management.IdentifierType;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.ImageDocument;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.javastrike.pdfblitz.manager.model.TextDocument;
import com.javastrike.pdfblitz.service.api.ConversionService;
import com.javastrike.pdfblitz.service.api.dto.DocumentDTO;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.javastrike.pdfblitz.service.api.paths.ConversionPaths.*;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@Component
@Path(CONVERSION_SERVICE_ROOT)
@Produces({MediaType.APPLICATION_XML})
public class ConversionServiceDefault implements ConversionService {


    private DocumentManager documentManager;

    public ConversionServiceDefault() {
        this.documentManager = new DocumentManager();
    }

    @Path(PDF_TO_IMAGES)
    @POST
    @Override
    public List<ImageDocument> convertDocumentToImages(DocumentDTO<PdfDocument> documentDTO) throws ConversionException {

        PdfDocument pdfDocument = documentDTO.getDocuments().get(0);
        int[] pageIndices = (int[]) documentDTO.getConversionContext().
                getConversionParameter(IdentifierType.PAGE_INDICES).getValue();
        return documentManager.getConversionOperations().convertDocumentToImages(pdfDocument, pageIndices);
    }

    @Path(IMAGES_TO_PDF)
    @POST
    @Override
    public PdfDocument convertImagesToPdfDocument(DocumentDTO<ImageDocument> documentDTO) throws ConversionException {
        return documentManager.getConversionOperations().convertImagesToPdfDocument(documentDTO.getDocuments(),
                documentDTO.getConversionContext());
    }

    @Path(TEXT_TO_PDF)
    @POST
    @Override
    public PdfDocument convertTextToPdfDocument(DocumentDTO<TextDocument> documentDTO) throws ConversionException {
        return documentManager.getConversionOperations().convertTextToPdfDocument(documentDTO.getDocuments().get(0),
                documentDTO.getConversionContext());
    }
}
