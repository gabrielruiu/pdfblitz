package com.javastrike.pdfblitz.frontend.pages;

import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public abstract class PdfBlitzPage extends Window {

    private String pageName;

    protected PdfBlitzPage(String caption, String pageName) {

        super(caption);
        this.pageName = pageName;
        setName(pageName);
    }

    public String getPageName(){
        return pageName;
    };
}
