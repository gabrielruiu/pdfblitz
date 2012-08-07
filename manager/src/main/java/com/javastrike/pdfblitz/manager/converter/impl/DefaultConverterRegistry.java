package com.javastrike.pdfblitz.manager.converter.impl;

import java.util.HashSet;
import java.util.Set;

import com.javastrike.pdfblitz.manager.converter.Converter;
import com.javastrike.pdfblitz.manager.converter.management.ConverterRegistry;
import com.javastrike.pdfblitz.manager.model.Document;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DefaultConverterRegistry implements ConverterRegistry {


    private Set<Converter> converters;

    public DefaultConverterRegistry() {
    }

    public DefaultConverterRegistry(Set<Converter> converters) {
        this.converters = converters;
    }

    @Override
    public void registerDocumentConverter(Converter documentConverter) {
        if (converters == null) {
            converters = new HashSet<Converter>();
        }
        converters.add(documentConverter);
    }

    @Override
    public void removeDocumentConverter(Converter documentConverter) {
        converters.remove(documentConverter);
    }

	@Override
    public Set<Converter> getDocumentConverters(Class<? extends Document> documentType) {

        Set<Converter> specificDocumentTypeConverters = new HashSet<Converter>();
        for ( Converter converter : converters){
            if (converter.handlesDocumentType(documentType)){
                specificDocumentTypeConverters.add(converter);
            }
        }
        return specificDocumentTypeConverters;
    }

    @Override
    public Set<Converter> getDocumentConverters() {
        return converters;
    }
}
