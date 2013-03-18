package com.javastrike.pdfblitz.service.api.paths;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public abstract class PdfPaths {

    private PdfPaths() {
        throw new AssertionError("PdfPaths class instantiation is restricted");
    }

    public static final String PDF_SERVICE_ROOT = "/pdf-service";

    public static final String EXTRACT_PAGES = "/extractPages";

    public static final String DELETE_PAGES = "/deletePages";

    public static final String MERGE_DOCUMENTS = "/mergeDocuments";

    public static final String SPLIT_AT_PAGES = "/splitAtPages";

    public static final String ENCRYPT = "/encrypt";

    public static final String DECRYPT = "/decrypt";
}
