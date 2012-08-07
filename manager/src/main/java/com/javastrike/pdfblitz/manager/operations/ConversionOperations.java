package com.javastrike.pdfblitz.manager.operations;

import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.exception.pdfoperations.PdfDocumentOperationException;
import com.javastrike.pdfblitz.manager.model.ImageDocument;
import com.javastrike.pdfblitz.manager.model.PdfDocument;

import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public interface ConversionOperations extends ConversionSupport {


    List<ImageDocument> convertDocumentToImages(PdfDocument pdfDocument) throws ConversionException;

    List<ImageDocument> convertDocumentToImages(PdfDocument pdfDocument, int startIndex, int endIndex)
            throws ConversionException;

    List<ImageDocument> convertDocumentToImages(PdfDocument pdfDocument, int... pageIndices)
            throws ConversionException;

    PdfDocument convertImagesToPdfDocument(List<ImageDocument> images, ConversionContext context)
            throws ConversionException;
}
