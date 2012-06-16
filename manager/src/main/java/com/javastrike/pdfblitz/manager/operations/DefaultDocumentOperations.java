package com.javastrike.pdfblitz.manager.operations;

import com.javastrike.pdfblitz.manager.model.Document;

import java.io.File;

public class DefaultDocumentOperations implements DocumentOperations{

    @Override
    public File createTemporaryFileLocation(Document document) {
        return null;
    }

    @Override
    public void deletePageFromDocument(Document document, int pageIndex) {

    }

    @Override
    public void createEmptyPage(Document document, int pageIndex) {

    }
}
