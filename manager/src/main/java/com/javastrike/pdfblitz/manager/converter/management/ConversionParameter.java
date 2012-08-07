package com.javastrike.pdfblitz.manager.converter.management;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public interface ConversionParameter<TYPE> {

    TYPE getValue();

    void setValue(TYPE type);
}
