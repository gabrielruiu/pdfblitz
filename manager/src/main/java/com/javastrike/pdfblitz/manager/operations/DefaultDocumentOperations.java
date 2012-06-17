package com.javastrike.pdfblitz.manager.operations;

import com.javastrike.pdfblitz.manager.model.Document;

public class DefaultDocumentOperations implements DocumentOperations{


    private Document document;

    public DefaultDocumentOperations(Document document) {
        this.document = document;
    }

    @Override
    public void deletePage(int pageIndex) {

    }

    @Override
    public void addEmptyPage(int pageIndex) {

    }
}
