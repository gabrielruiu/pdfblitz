package com.javastrike.pdfblitz.service.api;

import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.ImageDocument;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.javastrike.pdfblitz.manager.model.TextDocument;
import com.javastrike.pdfblitz.service.api.dto.DocumentDTO;

import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@1and1.ro)
 */
public interface ConversionService {

    List<ImageDocument> convertDocumentToImages(DocumentDTO<PdfDocument> documentDTO) throws ConversionException;

    PdfDocument convertImagesToPdfDocument(DocumentDTO<ImageDocument> documentDTO) throws ConversionException;

    PdfDocument convertTextToPdfDocument(DocumentDTO<TextDocument> documentDTO) throws ConversionException;
}
