package com.javastrike.pdfblitz.frontend.components;

import com.itextpdf.text.pdf.ByteBuffer;
import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.frontend.pages.DocumentEditorPage;
import com.javastrike.pdfblitz.manager.model.Document;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Handles the upload of the PDF file
 */
//TODO: organize FileUploadWindow
//TODO: separate file upload functionality from window
public class FileUploadWindow extends Window {

    private Logger logger = LoggerFactory.getLogger(FileUploadWindow.class);

    private Label uploadState;
    private Label result;
    private Label fileName;
    private Label textualProgress;
    private Upload uploadField;
    private ProgressIndicator progressIndicator;
    private Button cancelUploadButton;
    private BasicUploadReceiver counter;

    // this will hold the actual data from a PDF file
    private ByteBuffer documentContent;


    public FileUploadWindow(){
        this("Upload your PDF file");
    }

    public FileUploadWindow(String caption) {

        super(caption);
        initializeComponents();
        configureLayout();
        drawContents();
    }

    private void initializeComponents(){

        counter = new BasicUploadReceiver();
        uploadField = new Upload(null,counter);
        configureUploadListeners();
        cancelUploadButton = new Button("Cancel");
        progressIndicator = new ProgressIndicator();
        uploadState = new Label();
        result = new Label();
        fileName = new Label();
        textualProgress = new Label();
        documentContent = new ByteBuffer();
    }

    private void configureUploadListeners(){

        uploadField.addListener(new Upload.StartedListener() {
            @Override
            public void uploadStarted(Upload.StartedEvent event) {

                // this method gets called immediatedly after upload is started
                progressIndicator.setValue(0f);
                progressIndicator.setVisible(true);
                progressIndicator.setPollingInterval(500); // hit server frequantly to get
                textualProgress.setVisible(true);
                // updates to client
                uploadState.setValue("Uploading");
                fileName.setValue(event.getFilename());

                cancelUploadButton.setVisible(true);
            }
        });

        uploadField.addListener(new Upload.ProgressListener() {
            @Override
            public void updateProgress(long readBytes, long contentLength) {

                // this method gets called several times during the update
                progressIndicator.setValue(new Float(readBytes / (float) contentLength));
                textualProgress.setValue("Processed " + readBytes + " bytes of " + contentLength);
            }
        });


        uploadField.addListener(new Upload.SucceededListener() {
            @Override
            public void uploadSucceeded(Upload.SucceededEvent event) {
                result.setValue("File uploaded");
            }
        });

        uploadField.addListener(new Upload.FailedListener() {
            @Override
            public void uploadFailed(Upload.FailedEvent event) {
                result.setValue("Upload interrupted");
            }
        });

        uploadField.addListener(new Upload.FinishedListener() {
            @Override
            public void uploadFinished(Upload.FinishedEvent event) {
                uploadState.setValue("Idle");
                progressIndicator.setVisible(false);
                textualProgress.setVisible(false);
                cancelUploadButton.setVisible(false);
                setDocument(event.getFilename(),event.getMIMEType());
            }
        });
    }

    private void configureLayout(){
        setModal(true);
        center();
        setWidth("320px");
        setHeight("300px");
    }

    private void drawContents(){

        // main layout
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setSizeFull();
        this.setContent(layout);

        //upload field
        uploadField.setImmediate(true);
        uploadField.setButtonCaption("Click to browse");
        addComponent(uploadField);

        //cancel upload button
        cancelUploadButton.addListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                uploadField.interruptUpload();
            }
        });
        cancelUploadButton.setVisible(false);
        cancelUploadButton.setStyleName("small");

        //panel
        Panel panel = new Panel("Status");
        panel.setSizeUndefined();
        FormLayout formLayout = new FormLayout();
        formLayout.setMargin(true);
        panel.setContent(formLayout);
        HorizontalLayout uploadStateLayout = new HorizontalLayout();
        uploadStateLayout.setSpacing(true);

        uploadStateLayout.addComponent(uploadState);
        uploadStateLayout.addComponent(cancelUploadButton);
        uploadStateLayout.setCaption("Current state");
        uploadState.setValue("Idle");
        formLayout.addComponent(uploadStateLayout);
        fileName.setCaption("File name");
        formLayout.addComponent(fileName);
        result.setCaption("Line breaks counted");
        formLayout.addComponent(result);

        //progress indicator
        progressIndicator.setCaption("Progress");
        progressIndicator.setVisible(false);
        formLayout.addComponent(progressIndicator);
        textualProgress.setVisible(false);
        formLayout.addComponent(textualProgress);

        addComponent(panel);
    }

    //TODO: check to see if the file is indeed a PDF file
    //TODO: refurbish code
    private void setDocument(String name, String MIMEtype){

        Document document = prepareDocument(name, MIMEtype);
        ((PdfBlitzApplication)getApplication()).setDocument(document);
        goToDocumentEditor(document);
        close();
    }

    //TODO: change the type of storage to a StreamResource
    private Document prepareDocument(String name, String MIMEtype) {

        Document document = new Document();
        document.setContent(documentContent.toByteArray());
        document.setName(name);
        document.setMIMEtype(MIMEtype);

        try {
            document.setDocumentLocation(File.createTempFile("document-",null));
        } catch (IOException e) {
            //TODO: show error message in case of exception
            logger.error("Error creating temporary file",e);
        }

        return document;
    }

    private void goToDocumentEditor(Document document){
        getApplication().getMainWindow().setContent(new DocumentEditorPage(document));
    }


    private class BasicUploadReceiver implements Upload.Receiver {

        public ByteBuffer receiveUpload(String filename, String MIMEType) {
            return documentContent;
        }
    }

}
