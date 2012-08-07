package com.javastrike.pdfblitz.manager.utils;

import com.javastrike.pdfblitz.manager.model.Document;

import java.text.MessageFormat;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public abstract class DocumentNameUtils {

    private DocumentNameUtils() {
        throw new AssertionError("Cannot instantiate " + DocumentNameUtils.class.getName());
    }

    //TODO: refactor when Document.mimeType will become an enum
    public static String addNumberPrefixToDocumentName(Document document, int numberPrefix) {

        String[] nameSplit = document.getName().split("\\.");
        String name = nameSplit[0];
        String extendsion = nameSplit[1];

        return MessageFormat.format("{0}_{1}.{2}",name, numberPrefix, extendsion );
    }
}
