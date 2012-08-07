package com.javastrike.pdfblitz.manager.converter.impl;

import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.converter.management.ConversionParameter;
import com.javastrike.pdfblitz.manager.converter.management.IdentifierType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DefaultConversionContext implements ConversionContext {


    private Map<IdentifierType,ConversionParameter> conversionParameterMap;

    public DefaultConversionContext() {
        this(new HashMap<IdentifierType, ConversionParameter>());
    }

    public DefaultConversionContext(Map<IdentifierType, ConversionParameter> conversionParameterMap) {
        this.conversionParameterMap = conversionParameterMap;
    }

    @Override
    public ConversionContext addConversionParameter(IdentifierType identifier, ConversionParameter parameter) {

        conversionParameterMap.put(identifier,parameter);
        return this;
    }

    @Override
    public ConversionContext removeConversionParameter(IdentifierType identifier) {

        conversionParameterMap.remove(identifier);
        return this;
    }

    @Override
    public ConversionParameter getConversionParameter(IdentifierType identifier) {
        return conversionParameterMap.get(identifier);
    }
}
