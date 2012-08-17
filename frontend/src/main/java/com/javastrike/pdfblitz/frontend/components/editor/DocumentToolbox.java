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

        setColumns(2);
        setSizeFull();
        setSpacing(true);
        setHeight(1000, UNITS_PIXELS);
    }


    private void drawContents() {

    	addButton(new ConvertImagesToPdfButton());
    	addButton(new ConvertPdfToImagesButton());
    	addButton(new ExtractPagesButton());
    	addButton(new MergeDocumentsButton());
        addButton(new SplitPagesButton());
        addButton(new DeletePagesButton());
        addButton(new ConvertTextToPdfButton());
    }

    private void addButton(Button button) {

        addComponent(button);
        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }
}
