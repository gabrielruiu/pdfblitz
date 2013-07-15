package com.javastrike.pdfblitz.service.client.factory.impl;

import com.javastrike.pdfblitz.service.api.PdfDocumentService;
import com.javastrike.pdfblitz.service.client.factory.PdfServiceClientFactory;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class RestEasyPdfServiceClientFactory implements PdfServiceClientFactory {

    @Override
    public PdfDocumentService getPdfServiceClient(String baseURL) {

        RegisterBuiltin.register(ResteasyProviderFactory.getInstance());

        PdfDocumentService pdfServiceClient = ProxyFactory.create(PdfDocumentService.class, baseURL);
        return pdfServiceClient;
    }
}
