package com.javastrike.pdfblitz.manager.exception;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DocumentOperationException extends Exception {

    public DocumentOperationException() {
    }

    public DocumentOperationException(String message) {
        super(message);
    }

    public DocumentOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentOperationException(Throwable cause) {
        super(cause);
    }
}
