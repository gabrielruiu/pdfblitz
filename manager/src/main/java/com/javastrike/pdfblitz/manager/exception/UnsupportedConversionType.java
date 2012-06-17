package com.javastrike.pdfblitz.manager.exception;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class UnsupportedConversionType extends Exception {


    public UnsupportedConversionType() {
    }

    public UnsupportedConversionType(String message) {
        super(message);
    }

    public UnsupportedConversionType(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedConversionType(Throwable cause) {
        super(cause);
    }
}
