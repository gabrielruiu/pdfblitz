package com.javastrike.pdfblitz.service.client.factory;

import com.javastrike.pdfblitz.service.api.PdfDocumentService;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public interface PdfServiceClientFactory {

    PdfDocumentService getPdfServiceClient(String baseURL);
}
