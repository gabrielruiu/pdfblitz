package com.javastrike.pdfblitz.service.client;

import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.ImageDocument;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.javastrike.pdfblitz.manager.model.TextDocument;
import com.javastrike.pdfblitz.service.api.ConversionService;
import com.javastrike.pdfblitz.service.api.dto.DocumentDTO;
import com.javastrike.pdfblitz.service.api.paths.ConversionPaths;

import java.util.List;

import static com.javastrike.pdfblitz.service.api.paths.ConversionPaths.CONVERSION_SERVICE_ROOT;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@1and1.ro)
 */
public class ConversionServiceClient extends AbstractGateway implements ConversionService {


    public ConversionServiceClient(String baseURL) {
        super(baseURL);
    }

    @Override
    public List<ImageDocument> convertDocumentToImages(DocumentDTO<PdfDocument> documentDTO) throws ConversionException {
        return post(CONVERSION_SERVICE_ROOT.concat(ConversionPaths.PDF_TO_IMAGES), documentDTO, List.class);
    }

    @Override
    public PdfDocument convertImagesToPdfDocument(DocumentDTO<ImageDocument> documentDTO) throws ConversionException {
        return post(CONVERSION_SERVICE_ROOT.concat(ConversionPaths.IMAGES_TO_PDF), documentDTO, PdfDocument.class);
    }

    @Override
    public PdfDocument convertTextToPdfDocument(DocumentDTO<TextDocument> documentDTO) throws ConversionException {
        return post(CONVERSION_SERVICE_ROOT.concat(ConversionPaths.TEXT_TO_PDF), documentDTO, PdfDocument.class);
    }

    @Override
    public <T> T post(String resourceUrl, Object requestBody, Class<T> returnType) throws ConversionException {
        try{
            return super.post(resourceUrl, requestBody, returnType);
        } catch (Exception e) {
            throw new ConversionException(e);
        }
    }
}
