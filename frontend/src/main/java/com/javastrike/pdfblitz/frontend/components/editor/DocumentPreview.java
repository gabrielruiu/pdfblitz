package com.javastrike.pdfblitz.frontend.components.editor;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.manager.model.Document;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

//TODO: lazy loading component
public class DocumentPreview extends VerticalLayout {


    private Document document;

    public DocumentPreview(Document fileResource) {

        this.document = fileResource;
        configureLayout();
        drawContents();
    }

    private void configureLayout(){
        setSizeFull();
    }

    private void drawContents(){

        if (document != null){
            Embedded documentPreview = new Embedded();
            documentPreview.setSizeFull();
            documentPreview.setMimeType("application/pdf");
            documentPreview.setType(Embedded.TYPE_BROWSER);
            documentPreview.setParameter("frameborder", "0");
            documentPreview.setParameter("scrolling","auto");
            documentPreview.setSource(generateResourceFromDocument(document));
            addComponent(documentPreview);
        }
        else {
            //TODO: add a link that suggests uploading a file opens a window to upload the file
            addComponent(new Label("No document uploaded"));
        }
    }

    private StreamResource generateResourceFromDocument(final Document document) {

        StreamResource.StreamSource streamSource = new StreamResource.StreamSource() {
            @Override
            public InputStream getStream() {
                return new ByteArrayInputStream(document.getContent());
            }
        };
        StreamResource streamResource =
                new StreamResource(streamSource, document.getName(), PdfBlitzApplication.getInstance());
        return streamResource;
    }

}
