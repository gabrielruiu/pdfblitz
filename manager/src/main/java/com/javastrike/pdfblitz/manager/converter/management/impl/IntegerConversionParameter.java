package com.javastrike.pdfblitz.manager.converter.management.impl;

import com.javastrike.pdfblitz.manager.converter.management.ConversionParameter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@XmlRootElement
public class IntegerConversionParameter implements ConversionParameter<Integer> {


    private Integer value;

    public IntegerConversionParameter() {
    }

    public IntegerConversionParameter(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }
}
