package com.javastrike.pdfblitz.service.client.factory;

import com.javastrike.pdfblitz.service.api.ConversionService;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public interface ConversionServiceClientFactory {

    ConversionService getConversionServiceClient(String baseURL);
}
