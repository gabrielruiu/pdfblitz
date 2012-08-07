package com.javastrike.pdfblitz.manager.exception.pdfoperations;

import com.javastrike.pdfblitz.manager.exception.DocumentOperationException;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class PdfDocumentOperationException extends DocumentOperationException {

    public PdfDocumentOperationException() {
    }

    public PdfDocumentOperationException(String message) {
        super(message);
    }

    public PdfDocumentOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PdfDocumentOperationException(Throwable cause) {
        super(cause);
    }
}
