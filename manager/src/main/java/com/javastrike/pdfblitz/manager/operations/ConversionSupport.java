package com.javastrike.pdfblitz.manager.operations;

import com.javastrike.pdfblitz.manager.converter.management.ConverterResolver;

public interface ConversionSupport extends DocumentOperations {

    void setConverterResolver(ConverterResolver converterResolver);

    ConverterResolver getConverterResolver();
}
