package com.javastrike.pdfblitz.frontend.pages;

import com.javastrike.pdfblitz.frontend.components.editor.DocumentEditor;
import com.javastrike.pdfblitz.manager.model.Document;

public class DocumentEditorPage extends PdfBlitzPage {


    private static final String PAGE_NAME = "document-editor";

    public DocumentEditorPage(Document document) {

        super("PdfBlitz - Document Editor");
        addComponent(new DocumentEditor(document));
    }

    @Override
    String getPageName() {
        return PAGE_NAME;
    }
}
