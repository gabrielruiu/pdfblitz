package com.javastrike.pdfblitz.manager.converter.management.impl;

import com.javastrike.pdfblitz.manager.converter.management.ConversionParameter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@XmlRootElement
public class StringConversionParameter implements ConversionParameter<String>{


    private String value;

    public StringConversionParameter() {
    }

    public StringConversionParameter(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
