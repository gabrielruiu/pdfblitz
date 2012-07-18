package com.javastrike.pdfblitz.frontend.pages;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class PdfBlitzPage extends VerticalLayout {

    private MenuBar menuBar;

    public PdfBlitzPage() {
        initializeComponents();
        configureLayout();
        drawContents();
    }

    private void initializeComponents() {

        menuBar = new MenuBar();
        menuBar.setSizeFull();

        MenuBar.MenuItem home = menuBar.addItem("Return to Home Page", new ThemeResource("icons/16/home.png"),
                new MenuBar.Command() {
                    @Override
                    public void menuSelected(MenuBar.MenuItem selectedItem) {
                        getApplication().getMainWindow().setContent(new HomePage());
                    }
                });
    }

    private void configureLayout() {

    }

    private void drawContents() {
        addComponent(menuBar);
    }


}
