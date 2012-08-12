package com.javastrike.pdfblitz.frontend.zip.exception;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class CompressionException extends Exception {

    public CompressionException() {
    }

    public CompressionException(String message) {
        super(message);
    }

    public CompressionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompressionException(Throwable cause) {
        super(cause);
    }
}
