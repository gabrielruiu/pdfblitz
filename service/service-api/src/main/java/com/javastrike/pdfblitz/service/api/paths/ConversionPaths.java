package com.javastrike.pdfblitz.service.api.paths;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public abstract class ConversionPaths {

    private ConversionPaths() {
        throw new AssertionError("ConversionPaths class instantiation is restricted");
    }

    public static final String CONVERSION_SERVICE_ROOT = "/conversion-service";

    public static final String PDF_TO_IMAGES = "/pdfToImages";

    public static final String IMAGES_TO_PDF = "/imagesToPdf";

    public static final String TEXT_TO_PDF = "/textToPdf";
}
