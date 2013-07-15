package com.javastrike.pdfblitz.service.client.factory.impl;

import com.javastrike.pdfblitz.service.api.ConversionService;
import com.javastrike.pdfblitz.service.client.factory.ConversionServiceClientFactory;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class RestEasyConversionServiceClientFactory implements ConversionServiceClientFactory {

    @Override
    public ConversionService getConversionServiceClient(String baseURL) {

        RegisterBuiltin.register(ResteasyProviderFactory.getInstance());

        ConversionService conversionServiceClient = ProxyFactory.create(ConversionService.class, baseURL);
        return conversionServiceClient;
    }
}
