package com.javastrike.pdfblitz.frontend.components.editor;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.manager.exception.ConversionException;
import com.javastrike.pdfblitz.manager.exception.UnsupportedConversionType;
import com.javastrike.pdfblitz.manager.model.Document;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO: lazy loading component
public class DocumentPreview extends VerticalLayout {

    private Logger logger = LoggerFactory.getLogger(DocumentPreview.class);
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
            drawPreviewPane();
        }
        else {
            drawSuggestionText();
        }
    }


    private void drawPreviewPane(){

        Embedded documentPreview = new Embedded();
        documentPreview.setSizeFull();
        documentPreview.setMimeType(document.getMIMEtype());
        documentPreview.setType(Embedded.TYPE_BROWSER);
        documentPreview.setSource(generateResourceFromDocument(document));
        addComponent(documentPreview);
    }

    private void drawSuggestionText(){
        //TODO: add a link that suggests uploading a file opens a window to upload the file
        addComponent(new Label("No document uploaded"));
    }

    private StreamResource generateResourceFromDocument(final Document document) {

        StreamResource streamResource;
        try {
            streamResource = (StreamResource)((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                    getDocumentManager().getDocument(StreamResource.class);
        } catch (ConversionException e) {
            logger.error("Error converting document",e);
            streamResource = null;
        } catch (UnsupportedConversionType unsupportedConversionType) {
            logger.error("Error converting document", unsupportedConversionType);
            streamResource = null;
        }
        return streamResource;
    }

}
