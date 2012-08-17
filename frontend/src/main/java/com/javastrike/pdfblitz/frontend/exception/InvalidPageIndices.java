package com.javastrike.pdfblitz.frontend.exception;

import com.javastrike.pdfblitz.manager.exception.DocumentOperationException;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@1and1.ro)
 */
public class InvalidPageIndices extends DocumentOperationException {

    public InvalidPageIndices() {
    }

    public InvalidPageIndices(String message) {
        super(message);
    }

    public InvalidPageIndices(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPageIndices(Throwable cause) {
        super(cause);
    }
}
