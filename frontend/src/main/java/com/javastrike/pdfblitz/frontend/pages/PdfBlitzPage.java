package com.javastrike.pdfblitz.frontend.pages;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Window;

public abstract class PdfBlitzPage extends Window {

    protected PdfBlitzPage() {
        this("PdfBlitz Page");
    }

    protected PdfBlitzPage(String caption) {

        super(caption);
        setName(getPageName());
    }

    //TODO: bug: after navigating to a previous page using the Back button,
    public void goToPage(PdfBlitzPage pdfBlitzPage){

        getApplication().addWindow(pdfBlitzPage);
        open(new ExternalResource(pdfBlitzPage.getURL()));
    }

    abstract String getPageName();
}
