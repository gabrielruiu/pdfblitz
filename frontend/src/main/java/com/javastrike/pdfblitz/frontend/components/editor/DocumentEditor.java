package com.javastrike.pdfblitz.frontend.components.editor;

import com.javastrike.pdfblitz.frontend.components.editor.toolbox.DocumentToolbox;
import com.javastrike.pdfblitz.manager.model.Document;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;


public class DocumentEditor extends GridLayout {


    private Document document;

    public DocumentEditor(Document document) {

        this.document = document;
        configureLayout();
        drawContents();

    }

    private void configureLayout() {

        setColumns(2);
        setRows(1);
        setSizeFull();
        setSpacing(true);
        setHeight(800,UNITS_PIXELS);
        setColumnExpandRatio(0,0.2f);
        setColumnExpandRatio(1,0.8f);
    }


    private void drawContents() {

        addDocumentToolbox();
        addDocumentPreview();
    }

    private void addDocumentToolbox() {

        DocumentToolbox toolbox = new DocumentToolbox();
        addComponent(toolbox, 0, 0);
        setComponentAlignment(toolbox, Alignment.MIDDLE_CENTER);
    }

    private void addDocumentPreview() {

        DocumentPreview preview = new DocumentPreview(document);
        addComponent(preview,1,0);
        setComponentAlignment(preview, Alignment.MIDDLE_CENTER);
    }
}
