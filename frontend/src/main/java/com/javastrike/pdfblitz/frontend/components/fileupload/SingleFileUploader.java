package com.javastrike.pdfblitz.frontend.components.fileupload;

import com.javastrike.pdfblitz.frontend.PdfBlitzUI;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.model.MimeType;
import com.vaadin.ui.*;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;

/**
 * Component that handles single-file uploads.
 *
 * @see DocumentUploader
 * @see UploadType
 * @see FileUploaderFactory
 * @see FileUploader
 *
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@SuppressWarnings("serial")
public class SingleFileUploader extends FileUploader<Document>{


    private Document payload;

    private FormLayout formLayout;

    private Upload uploadButton;
    private Label uploadState;
    private Label fileName;
    private Label textualProgress;
    private ProgressIndicator progressIndicator;
    private Button cancelUploadButton;
    private SingleUploadReceiver uploadReceiver;

    private ByteArrayOutputStream contentStream;

    public SingleFileUploader() {

        initializeComponents();
        configureLayout();
        drawContents();
    }

    @Override
    public Document getPayload() {

        payload.setContent(contentStream.toByteArray());
        return payload;
    }

    @Override
    public void addListener(Upload.StartedListener startedListener) {
        uploadButton.addListener(startedListener);
    }

    @Override
    public void addListener(Upload.ProgressListener progressListener) {
        uploadButton.addListener(progressListener);
    }

    @Override
    public void addListener(Upload.FinishedListener finishedListener) {
        uploadButton.addListener(finishedListener);
    }

    @Override
    public void addListener(Upload.FailedListener failedListener) {
        uploadButton.addListener(failedListener);
    }

    @Override
    public void addListener(Upload.SucceededListener succeededListener) {
        uploadButton.addListener(succeededListener);
    }

    private void configureLayout(){

        formLayout.setMargin(true);
        formLayout.setSpacing(true);
        formLayout.setSizeUndefined();
    }

    private void initializeComponents(){

        contentStream = new ByteArrayOutputStream();
        formLayout = new FormLayout();
        uploadReceiver = new SingleUploadReceiver();
        uploadButton = new Upload(null, uploadReceiver);
        configureUploadListeners();
        cancelUploadButton = new Button(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getMessage("fileuploader.single.button.cancel"));
        progressIndicator = new ProgressIndicator();
        uploadState = new Label();
        fileName = new Label();
        textualProgress = new Label();
    }

    private void configureUploadListeners(){

        uploadButton.addListener(new Upload.StartedListener() {
            @Override
            public void uploadStarted(Upload.StartedEvent event) {

                // this method gets called immediately after upload is started
                progressIndicator.setValue(0f);
                progressIndicator.setVisible(true);
                progressIndicator.setPollingInterval(500); // hit server frequently to get
                textualProgress.setVisible(true);
                // updates to client
                uploadState.setValue(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                        getMessage("fileuploader.single.upload.started"));
                fileName.setValue(event.getFilename());

                cancelUploadButton.setVisible(true);
            }
        });

        uploadButton.addListener(new Upload.ProgressListener() {
            @Override
            public void updateProgress(long readBytes, long contentLength) {

                // this method gets called several times during the update
                progressIndicator.setValue(new Float(readBytes / (float) contentLength));
                textualProgress.setValue(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                        getMessage("fileuploader.single.upload.progress", new Object[]{String.valueOf(readBytes),
                                String.valueOf(contentLength)}));
            }
        });


        uploadButton.addListener(new Upload.SucceededListener() {
            @Override
            public void uploadSucceeded(Upload.SucceededEvent event) {

            }
        });

        uploadButton.addListener(new Upload.FailedListener() {
            @Override
            public void uploadFailed(Upload.FailedEvent event) {

            }
        });

        uploadButton.addListener(new Upload.FinishedListener() {
            @Override
            public void uploadFinished(Upload.FinishedEvent event) {
                IOUtils.closeQuietly(contentStream);
                uploadState.setValue(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                        getMessage("fileuploader.single.upload.finished"));
                progressIndicator.setVisible(false);
                textualProgress.setVisible(false);
                cancelUploadButton.setVisible(false);
            }
        });
    }

    private void drawContents(){

        //upload field
        uploadButton.setImmediate(true);
        uploadButton.setButtonCaption(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getMessage("fileuploader.single.button.browse"));
        formLayout.addComponent(uploadButton);

        //cancel upload button
        cancelUploadButton.addListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                uploadButton.interruptUpload();
            }
        });
        cancelUploadButton.setVisible(false);
        cancelUploadButton.setStyleName("small");

        //panel
        formLayout.setMargin(true);
        HorizontalLayout uploadStateLayout = new HorizontalLayout();
        uploadStateLayout.setSpacing(true);

        uploadStateLayout.addComponent(uploadState);
        uploadStateLayout.addComponent(cancelUploadButton);
        uploadStateLayout.setCaption(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getMessage("fileuploader.single.panel.currentstate"));
        uploadState.setValue(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getMessage("fileuploader.single.upload.finished"));
        formLayout.addComponent(uploadStateLayout);
        fileName.setCaption(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getMessage("fileuploader.single.panel.filename"));
        formLayout.addComponent(fileName);


        //progress indicator
        progressIndicator.setCaption(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getMessage("fileuploader.single.progressindicator.caption"));
        progressIndicator.setVisible(false);
        formLayout.addComponent(progressIndicator);
        textualProgress.setVisible(false);
        formLayout.addComponent(textualProgress);

        addComponent(formLayout);
    }


    private class SingleUploadReceiver implements Upload.Receiver {

        public ByteArrayOutputStream receiveUpload(String filename, String MIMEType) {

            contentStream = new ByteArrayOutputStream();
            payload = new Document();
            payload.setName(filename);
            payload.setMimeType(MimeType.UNKNOWN);
            return contentStream;
        }
    }
}
