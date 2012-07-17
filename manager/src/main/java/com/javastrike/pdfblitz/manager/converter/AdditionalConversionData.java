package com.javastrike.pdfblitz.manager.converter;

import java.util.HashMap;

/**
 * Class that holds the additional data needed to make a conversion from a certain type to a Document.
 *
 * @see DocumentConverter
 *
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */

public class AdditionalConversionData extends HashMap<String, String> {

    public static final String NAME = "name";
    public static final String MIME_TYPE = "mimeType";
}
