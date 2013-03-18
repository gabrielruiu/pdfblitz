package com.javastrike.pdfblitz.manager.converter.management;

import com.javastrike.pdfblitz.manager.converter.management.jaxb.ConversionContextXmlAdapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@XmlRootElement
public class ConversionContext {

    @XmlJavaTypeAdapter(ConversionContextXmlAdapter.class)
    private Map<IdentifierType,ConversionParameter> conversionParameterMap;

    public ConversionContext() {
        this(new HashMap<IdentifierType, ConversionParameter>());
    }

    public ConversionContext(Map<IdentifierType, ConversionParameter> conversionParameterMap) {
        this.conversionParameterMap = conversionParameterMap;
    }

    public ConversionContext addConversionParameter(IdentifierType identifier, ConversionParameter parameter) {

        conversionParameterMap.put(identifier,parameter);
        return this;
    }

    public ConversionContext removeConversionParameter(IdentifierType identifier) {

        conversionParameterMap.remove(identifier);
        return this;
    }

    public ConversionParameter getConversionParameter(IdentifierType identifier) {
        return conversionParameterMap.get(identifier);
    }
}
