package com.javastrike.pdfblitz.frontend.pages;

import com.javastrike.pdfblitz.frontend.components.ApplicationMenuBar;
import com.vaadin.ui.VerticalLayout;

public abstract class PdfBlitzPage extends VerticalLayout {


    public PdfBlitzPage() {
        addComponent(new ApplicationMenuBar());
    }
}
