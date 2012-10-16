package com.javastrike.pdfblitz.frontend.components.fileupload;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.manager.model.Document;
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


    private VerticalLayout verticalLayout;
    private Upload uploadField;

    private Label uploadState;
    private Label result;
    private Label fileName;
    private Label textualProgress;
    private ProgressIndicator progressIndicator;
    private Button cancelUploadButton;
    private SingleUploadReceiver uploadReceiver;
    private ByteArrayOutputStream contentStream;

    private Document payload;

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
        uploadField.addListener(startedListener);
    }

    @Override
    public void addListener(Upload.ProgressListener progressListener) {
        uploadField.addListener(progressListener);
    }

    @Override
    public void addListener(Upload.FinishedListener finishedListener) {
        uploadField.addListener(finishedListener);
    }

    @Override
    public void addListener(Upload.FailedListener failedListener) {
        uploadField.addListener(failedListener);
    }

    @Override
    public void addListener(Upload.SucceededListener succeededListener) {
        uploadField.addListener(succeededListener);
    }

    private void configureLayout(){

        verticalLayout.setMargin(true);
        verticalLayout.setSpacing(true);
        verticalLayout.setSizeUndefined();
    }

    private void initializeComponents(){

        contentStream = new ByteArrayOutputStream();
        verticalLayout = new VerticalLayout();
        uploadReceiver = new SingleUploadReceiver();
        uploadField = new Upload(null, uploadReceiver);
        configureUploadListeners();
        cancelUploadButton = new Button(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getMessage("fileuploader.single.button.cancel"));
        progressIndicator = new ProgressIndicator();
        uploadState = new Label();
        result = new Label();
        fileName = new Label();
        textualProgress = new Label();
    }

    private void configureUploadListeners(){

        uploadField.addListener(new Upload.StartedListener() {
            @Override
            public void uploadStarted(Upload.StartedEvent event) {

                // this method gets called immediately after upload is started
                progressIndicator.setValue(0f);
                progressIndicator.setVisible(true);
                progressIndicator.setPollingInterval(500); // hit server frequently to get
                textualProgress.setVisible(true);
                // updates to client
                uploadState.setValue(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                        getMessage("fileuploader.single.upload.started"));
                fileName.setValue(event.getFilename());

                cancelUploadButton.setVisible(true);
            }
        });

        uploadField.addListener(new Upload.ProgressListener() {
            @Override
            public void updateProgress(long readBytes, long contentLength) {

                // this method gets called several times during the update
                progressIndicator.setValue(new Float(readBytes / (float) contentLength));
                textualProgress.setValue(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                        getMessage("fileuploader.single.upload.progress",new Object[] {String.valueOf(readBytes),
                                String.valueOf(contentLength)}));
            }
        });


        uploadField.addListener(new Upload.SucceededListener() {
            @Override
            public void uploadSucceeded(Upload.SucceededEvent event) {
                result.setValue(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                        getMessage("fileuploader.single.upload.succeded"));
            }
        });

        uploadField.addListener(new Upload.FailedListener() {
            @Override
            public void uploadFailed(Upload.FailedEvent event) {
                result.setValue(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                        getMessage("fileuploader.single.upload.interrupted"));
            }
        });

        uploadField.addListener(new Upload.FinishedListener() {
            @Override
            public void uploadFinished(Upload.FinishedEvent event) {
                IOUtils.closeQuietly(contentStream);
                uploadState.setValue(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                        getMessage("fileuploader.single.upload.finished"));
                progressIndicator.setVisible(false);
                textualProgress.setVisible(false);
                cancelUploadButton.setVisible(false);
            }
        });
    }

    private void drawContents(){

        //upload field
        uploadField.setImmediate(true);
        uploadField.setButtonCaption(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getMessage("fileuploader.single.button.browse"));
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
        Panel panel = new Panel(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getMessage("fileuploader.single.panel.title"));
        panel.setSizeUndefined();
        FormLayout formLayout = new FormLayout();
        formLayout.setMargin(true);
        panel.setContent(formLayout);
        HorizontalLayout uploadStateLayout = new HorizontalLayout();
        uploadStateLayout.setSpacing(true);

        uploadStateLayout.addComponent(uploadState);
        uploadStateLayout.addComponent(cancelUploadButton);
        uploadStateLayout.setCaption(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getMessage("fileuploader.single.panel.currentstate"));
        uploadState.setValue(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getMessage("fileuploader.single.upload.finished"));
        formLayout.addComponent(uploadStateLayout);
        fileName.setCaption(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getMessage("fileuploader.single.panel.filename"));
        formLayout.addComponent(fileName);
        result.setCaption(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getMessage("fileuploader.single.panel.linebreaks"));
        formLayout.addComponent(result);

        //progress indicator
        progressIndicator.setCaption(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getMessage("fileuploader.single.progressindicator.caption"));
        progressIndicator.setVisible(false);
        formLayout.addComponent(progressIndicator);
        textualProgress.setVisible(false);
        formLayout.addComponent(textualProgress);

        addComponent(panel);
    }


    private class SingleUploadReceiver implements Upload.Receiver {

        public ByteArrayOutputStream receiveUpload(String filename, String MIMEType) {

            contentStream = new ByteArrayOutputStream();
            payload = new Document();
            payload.setName(filename);
            payload.setMimeType(MIMEType);
            return contentStream;
        }
    }
}
