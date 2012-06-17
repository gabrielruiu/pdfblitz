package com.javastrike.pdfblitz.frontend.pages;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.frontend.theme.PdfBlitzTheme;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class HomePage extends VerticalLayout {


    public HomePage() {

        setSizeFull();

        /**
         * Button for accessing the page with tools for operating on SINGLE files
         */
        Button singleFileOperations = new Button("Single", new Button.ClickListener(){
            @Override
            public void buttonClick(Button.ClickEvent event) {
                    getApplication().getMainWindow().setContent(
                            new DocumentEditorPage(
                                    ((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).getDocument()));
            }
        });
        singleFileOperations.addStyleName(PdfBlitzTheme.BUTTON_HOMEPAGE);
        addComponent(singleFileOperations);


        /**
         * Button for accessing the page with tools for operating on MULTIPLE files
         */
        Button multipleFilesOperations = new Button("Multiple", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

            }
        });
        multipleFilesOperations.addStyleName(PdfBlitzTheme.BUTTON_HOMEPAGE);
        addComponent(multipleFilesOperations);

    }
}
