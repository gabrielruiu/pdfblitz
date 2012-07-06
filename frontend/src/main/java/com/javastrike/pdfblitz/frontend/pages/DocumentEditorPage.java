package com.javastrike.pdfblitz.frontend.pages;

import com.javastrike.pdfblitz.frontend.components.editor.DocumentEditor;
import com.javastrike.pdfblitz.manager.model.Document;

@SuppressWarnings("serial")
public class DocumentEditorPage extends PdfBlitzPage {


    public static final String PAGE_NAME = "document-editor";

    public DocumentEditorPage(Document document) {

        super("PdfBlitz - Document Editor", PAGE_NAME);
        addComponent(new DocumentEditor(document));
    }
}
