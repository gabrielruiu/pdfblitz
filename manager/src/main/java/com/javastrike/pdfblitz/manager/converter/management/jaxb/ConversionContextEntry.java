package com.javastrike.pdfblitz.manager.converter.management.jaxb;

import com.javastrike.pdfblitz.manager.converter.management.ConversionParameter;
import com.javastrike.pdfblitz.manager.converter.management.IdentifierType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ConversionContextEntry {

    private IdentifierType identifierType;
    private ConversionParameter<?> conversionParameter;

    public ConversionContextEntry() {
    }

    public ConversionContextEntry(IdentifierType identifierType, ConversionParameter<?> conversionParameter) {
        this.identifierType = identifierType;
        this.conversionParameter = conversionParameter;
    }

    @XmlElement
    public IdentifierType getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(IdentifierType identifierType) {
        this.identifierType = identifierType;
    }

    @XmlElement
    public ConversionParameter<?> getConversionParameter() {
        return conversionParameter;
    }

    public void setConversionParameter(ConversionParameter<?> conversionParameter) {
        this.conversionParameter = conversionParameter;
    }
}