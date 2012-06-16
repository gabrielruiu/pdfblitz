package com.javastrike.pdfblitz.manager.operations;

import com.javastrike.pdfblitz.manager.model.Document;

import java.io.File;

public interface DocumentOperations {

    File createTemporaryFileLocation(Document document);

    void deletePageFromDocument(Document document, int pageIndex);

    void createEmptyPage(Document document, int pageIndex);
}
