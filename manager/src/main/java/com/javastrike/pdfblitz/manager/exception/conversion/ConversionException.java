package com.javastrike.pdfblitz.manager.exception.conversion;

import com.javastrike.pdfblitz.manager.exception.DocumentOperationException;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ConversionException extends DocumentOperationException {

    public ConversionException() {
    }

    public ConversionException(String message) {
        super(message);
    }

    public ConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConversionException(Throwable cause) {
        super(cause);
    }
}
