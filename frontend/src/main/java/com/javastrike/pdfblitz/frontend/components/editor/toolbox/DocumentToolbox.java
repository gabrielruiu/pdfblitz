package com.javastrike.pdfblitz.frontend.components.editor.toolbox;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class DocumentToolbox extends VerticalLayout {


    private Button splitPagesButton;


    public DocumentToolbox() {

       configureLayout();
       initializeComponents();
       drawContents();
    }


    private void configureLayout() {
        setSizeFull();
    }

    private void initializeComponents() {

        splitPagesButton = new Button("Split PDF document", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

            }
        });
    }

    private void drawContents() {

    }
}
