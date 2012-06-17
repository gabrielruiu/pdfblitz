package com.javastrike.pdfblitz.frontend.components;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;

public class ApplicationMenuBar extends HorizontalLayout {


    public ApplicationMenuBar() {

        MenuBar applicationMenuBar = new MenuBar();
        addItemsToMenuBar(applicationMenuBar);

        addComponent(applicationMenuBar);
    }


    private void addItemsToMenuBar(MenuBar menuBar){

        // "File" button
        MenuBar.MenuItem file = menuBar.addItem("File",null);

        MenuBar.MenuItem upload = file.addItem("Upload from ...",null);
        upload.addItem("Computer",new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getApplication().getMainWindow().addWindow(new FileUploader("Upload a PDF file"));
            }
        });
        upload.addItem("Website URL",new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                //TODO: add website document retrieval
            }
        });


        // "Tools" button - contains additional tools for pdf operations, eg. document conversions
        MenuBar.MenuItem tools = menuBar.addItem("Tools",null);

        MenuBar.MenuItem convert = tools.addItem("Convert",null);
        convert.addItem("from JPEGs to PDF", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                //TODO: add conversion utilities: JPEGs to PDF
            }
        });
        convert.addItem("from PDF to JPEGs", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                //TODO: add conversion utilities: PDF to JPEGs
            }
        });
        convert.addSeparator();
        convert.addItem("from Word to PDF", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                //TODO: add conversion utilities: Word to PDF
            }
        });
        convert.addItem("from PDF to Word", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                //TODO: add conversion utilities: PDF to Word
            }
        });
        convert.addSeparator();
        convert.addItem("from Website to PDF", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                //TODO: add conversion utilities: Website to PDF
            }
        });
    }
}
