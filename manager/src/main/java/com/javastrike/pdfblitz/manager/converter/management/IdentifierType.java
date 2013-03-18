package com.javastrike.pdfblitz.manager.converter.management;

import javax.xml.bind.annotation.XmlEnum;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@XmlEnum
public enum IdentifierType {

    DOCUMENT_NAME,
    MIME_TYPE,

    IMAGE_FORMAT,
    IMAGE_OUTPUT_PREFIX,
    IMAGE_PAGE_NUMBER,
    PAGE_INDICES
}
