package com.javastrike.pdfblitz.manager.converter.management.impl;

import com.javastrike.pdfblitz.manager.converter.management.ConversionParameter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@1and1.ro)
 */
@XmlRootElement
public class IntegerArrayConversionParameter implements ConversionParameter<int[]> {


    private int[] value;


    public IntegerArrayConversionParameter() {
    }

    public IntegerArrayConversionParameter(int[] value) {
        this.value = value;
    }


    @Override
    public int[] getValue() {
        return value;
    }

    @Override
    public void setValue(int[] ints) {
        this.value = ints;
    }
}
