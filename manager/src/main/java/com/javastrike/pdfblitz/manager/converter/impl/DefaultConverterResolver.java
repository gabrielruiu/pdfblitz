package com.javastrike.pdfblitz.manager.converter.impl;

import com.javastrike.pdfblitz.manager.converter.Converter;
import com.javastrike.pdfblitz.manager.converter.management.ConverterRegistry;
import com.javastrike.pdfblitz.manager.converter.management.ConverterResolver;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DefaultConverterResolver implements ConverterResolver {


    private ConverterRegistry registry;

    public DefaultConverterResolver() {
        registry = new DefaultConverterRegistry();
    }

    public DefaultConverterResolver(ConverterRegistry registry) {
        this.registry = registry;
    }

    @Override
    public Converter getConverter(Class documentClass, Class conversionClass) {

        Converter documentConverter = null;
        for (Converter converter : registry.getDocumentConverters(documentClass)){
            if (converter.supports(conversionClass)){
                documentConverter = converter;
            }
        }
        return documentConverter;
    }

    @Override
    public void setConverterRegistry(ConverterRegistry registry) {
        this.registry = registry;
    }

    @Override
    public ConverterRegistry getConverterRegistry() {
        return registry;
    }
}
