package com.javastrike.pdfblitz.manager.exception.pdfoperations;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@1and1.ro)
 */
public class PageIndicesOutOfRangeException extends PdfDocumentOperationException {


    public PageIndicesOutOfRangeException() {
    }

    public PageIndicesOutOfRangeException(String message) {
        super(message);
    }

    public PageIndicesOutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageIndicesOutOfRangeException(Throwable cause) {
        super(cause);
    }
}
