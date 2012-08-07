package com.javastrike.pdfblitz.manager.converter.management;

import com.javastrike.pdfblitz.manager.converter.Converter;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public interface ConverterResolver {

    Converter getConverter(Class documentClass, Class conversionClass);

    void setConverterRegistry(ConverterRegistry registry);

    ConverterRegistry getConverterRegistry();
}
