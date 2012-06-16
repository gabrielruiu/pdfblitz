package com.javastrike.pdfblitz.frontend.components.editor;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DocumentToolbox extends VerticalLayout {

    public DocumentToolbox() {

        setSizeFull();
        addComponent(new Label("DocumentToolbox"));
    }
}
