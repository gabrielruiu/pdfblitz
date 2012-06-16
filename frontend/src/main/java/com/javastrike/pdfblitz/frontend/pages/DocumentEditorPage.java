package com.javastrike.pdfblitz.frontend.pages;

import com.javastrike.pdfblitz.frontend.components.editor.DocumentEditor;
import com.javastrike.pdfblitz.manager.model.Document;

public class DocumentEditorPage extends PdfBlitzPage {

    public DocumentEditorPage(Document document) {
        addComponent(new DocumentEditor(document));
    }
}
