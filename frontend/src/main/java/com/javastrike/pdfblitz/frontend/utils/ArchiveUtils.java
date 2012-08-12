package com.javastrike.pdfblitz.frontend.utils;

import java.text.MessageFormat;
import java.util.UUID;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public abstract class ArchiveUtils {


    private ArchiveUtils() {
        throw new AssertionError("Cannot instantiate " + getClass().getName());
    }

    //TODO: ApplicationPropertiesUtils()
    public static String generateFullPathForArchive() {
        return null;
    }

    public static String generateNameForArchive() {
        return MessageFormat.format("archive-{0}.zip",UUID.randomUUID().toString());
    }
}
