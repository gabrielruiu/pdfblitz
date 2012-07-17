package com.javastrike.pdfblitz.frontend.pages;

import com.javastrike.pdfblitz.frontend.components.fileupload.UploadType;
import com.javastrike.pdfblitz.frontend.components.home.HomePageButton;
import com.javastrike.pdfblitz.frontend.theme.PdfBlitzTheme;
import com.javastrike.pdfblitz.frontend.windows.FileUploadWindow;
import com.vaadin.event.LayoutEvents;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class HomePage extends PdfBlitzPage {


    public static final String PAGE_NAME = "homepage";

    private GridLayout layout;
    private HomePageButton singlePdfOperations;
    private HomePageButton conversionOperations;

    public HomePage() {

        super("PdfBlitz - select an operation", PAGE_NAME);
        initializeComponents();
        configureLayout();
        drawContents();
    }

    private void initializeComponents() {

        layout = new GridLayout();

        singlePdfOperations = new HomePageButton(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent event) {
                getWindow().addWindow(new FileUploadWindow(UploadType.SINGLE));
            }
        },
        "PDF tools",
        getSingleOperationsList()
        );
        singlePdfOperations.addStyleName(PdfBlitzTheme.BUTTON_HOMEPAGE_LEFT);

        conversionOperations = new HomePageButton(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent event) {
                getWindow().addWindow(new FileUploadWindow(UploadType.MULTIPLE));
            }
        },
        "Conversion tools",
        getConversionOperationsList());
        conversionOperations.addStyleName(PdfBlitzTheme.BUTTON_HOMEPAGE_RIGHT);
    }

    private void configureLayout() {

        layout.setSizeFull();
        layout.setColumns(2);
        layout.setRows(1);
        layout.setHeight(800,UNITS_PIXELS);
    }

    private void drawContents() {

        layout.addComponent(singlePdfOperations,0,0);
        layout.setComponentAlignment(singlePdfOperations, Alignment.MIDDLE_RIGHT);

        layout.addComponent(conversionOperations,1,0);
        layout.setComponentAlignment(conversionOperations,Alignment.MIDDLE_LEFT);

        addComponent(layout);
    }

    private List<String> getSingleOperationsList(){

        List<String> singleOperationsList = new ArrayList<String>();
        singleOperationsList.add("Delete or create new pages");
        singleOperationsList.add("Split a single file into several documents");
        singleOperationsList.add("Concatenate several PDF files");
        return singleOperationsList;
    }


    private List<String> getConversionOperationsList() {

        List<String> multipleOperationsList = new ArrayList<String>();
        multipleOperationsList.add("Upload several images and transform into PDF");
        multipleOperationsList.add("Convert a Microsoft Word document");
        multipleOperationsList.add("Download a website and turn it into a PDF");
        return multipleOperationsList;
    }
}
