package com.javastrike.pdfblitz.service.api;

import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.ImageDocument;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.javastrike.pdfblitz.manager.model.TextDocument;
import com.javastrike.pdfblitz.service.api.dto.DocumentDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.javastrike.pdfblitz.service.api.paths.ConversionPaths.*;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@1and1.ro)
 */
@Path(CONVERSION_SERVICE_ROOT)
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public interface ConversionService {

    @POST
    @Path(PDF_TO_IMAGES)
    List<ImageDocument> convertDocumentToImages(DocumentDTO<PdfDocument> documentDTO) throws ConversionException;

    @POST
    @Path(IMAGES_TO_PDF)
    PdfDocument convertImagesToPdfDocument(DocumentDTO<ImageDocument> documentDTO) throws ConversionException;

    @POST
    @Path(TEXT_TO_PDF)
    PdfDocument convertTextToPdfDocument(DocumentDTO<TextDocument> documentDTO) throws ConversionException;
}
