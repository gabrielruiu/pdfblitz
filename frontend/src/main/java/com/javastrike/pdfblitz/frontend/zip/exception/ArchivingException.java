package com.javastrike.pdfblitz.frontend.zip.exception;

import com.javastrike.pdfblitz.manager.exception.DocumentOperationException;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@1and1.ro)
 */
public class ArchivingException extends DocumentOperationException {

    public ArchivingException() {
    }

    public ArchivingException(String message) {
        super(message);
    }

    public ArchivingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArchivingException(Throwable cause) {
        super(cause);
    }
}
