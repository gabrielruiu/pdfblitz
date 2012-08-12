package com.javastrike.pdfblitz.frontend.components.editor;

import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.*;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;


public class DocumentToolbox extends GridLayout {


    public DocumentToolbox() {

        configureLayout();
        drawContents();
    }

    private void configureLayout() {

        setColumns(3);
        setSizeFull();
        setSpacing(true);
        setHeight(800, UNITS_PIXELS);
    }


    private void drawContents() {

        addButton(new SplitPagesButton());
        addButton(new DeletePagesButton());
        addButton(new ExtractPagesButton());
        addButton(new MergeDocumentsButton());
        addButton(new ConvertPdfToImagesButton());
        addButton(new ConvertImagesToPdfButton());
    }

    private void addButton(Button button) {

        addComponent(button);
        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }
}
