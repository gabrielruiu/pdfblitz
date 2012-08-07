package com.javastrike.pdfblitz.manager.exception.pdfoperations;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class EncryptedException extends PdfDocumentOperationException {

    public EncryptedException() {
    }

    public EncryptedException(String message) {
        super(message);
    }

    public EncryptedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncryptedException(Throwable cause) {
        super(cause);
    }
}
