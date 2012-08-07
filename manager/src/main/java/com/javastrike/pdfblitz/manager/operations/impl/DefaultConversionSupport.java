package com.javastrike.pdfblitz.manager.operations.impl;

import com.javastrike.pdfblitz.manager.converter.impl.DefaultConverterResolver;
import com.javastrike.pdfblitz.manager.converter.impl.document.DocumentByteStreamConverter;
import com.javastrike.pdfblitz.manager.converter.impl.document.DocumentFileConverter;
import com.javastrike.pdfblitz.manager.converter.impl.document.DocumentStringConverter;
import com.javastrike.pdfblitz.manager.converter.impl.pdf.PdfToImageConverter;
import com.javastrike.pdfblitz.manager.converter.impl.pdfbox.ImageToPDDConverter;
import com.javastrike.pdfblitz.manager.converter.impl.pdfbox.PdfToPDDConverter;
import com.javastrike.pdfblitz.manager.converter.management.ConverterResolver;
import com.javastrike.pdfblitz.manager.operations.ConversionSupport;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DefaultConversionSupport implements ConversionSupport {


    private ConverterResolver converterResolver;

    public DefaultConversionSupport() {
        this(new DefaultConverterResolver());
    }

    public DefaultConversionSupport(ConverterResolver converterResolver) {
    	
        this.converterResolver = converterResolver;
        registerDefaultConverters();
    }

    private void registerDefaultConverters(){

        // document converters
        converterResolver.getConverterRegistry().registerDocumentConverter(new DocumentFileConverter());
        converterResolver.getConverterRegistry().registerDocumentConverter(new DocumentByteStreamConverter());
        converterResolver.getConverterRegistry().registerDocumentConverter(new DocumentStringConverter());

        //ImageDocument converters
        converterResolver.getConverterRegistry().registerDocumentConverter(new ImageToPDDConverter());
        
        //PdfDocument converters
        converterResolver.getConverterRegistry().registerDocumentConverter(new PdfToImageConverter());
        converterResolver.getConverterRegistry().registerDocumentConverter(new PdfToPDDConverter());
    }

    private void registerPdfBoxSpecificConverters() {


    }

    @Override
    public void setConverterResolver(ConverterResolver resolver) {
        this.converterResolver = resolver;
    }

    @Override
    public ConverterResolver getConverterResolver() {
        return converterResolver;
    }
}
