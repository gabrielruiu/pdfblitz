package com.javastrike.pdfblitz.frontend.components.fileupload;

import com.javastrike.pdfblitz.manager.model.Document;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Main component that exposes the upload functionality for the application.
 *
 * NOTE: Because of the difficulties of developing a proper component to upload multiple files at once,
 * I have settled, temporarily, for a component that uploads single files multiple times and shows a
 * list of the uploaded file names.
 *
 * @see UploadType
 * @see FileUploader
 *
 * @author  Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */

@SuppressWarnings("serial")
public class DocumentUploader extends VerticalLayout implements UploadEventHandler {


    private List<Document> payload;
    private UploadType uploadType;
    private FileUploader fileUploader;

    public DocumentUploader(UploadType uploadType){

        configureLayout(uploadType);
        initializeComponents();
        drawContents();
    }

    public List<Document> getPayload(){
        return payload;
    }

    @Override
    public void addListener(Upload.StartedListener startedListener) {
        fileUploader.addListener(startedListener);
    }

    @Override
    public void addListener(Upload.ProgressListener progressListener) {
        fileUploader.addListener(progressListener);
    }

    @Override
    public void addListener(Upload.FinishedListener finishedListener) {
        fileUploader.addListener(finishedListener);
    }

    @Override
    public void addListener(Upload.FailedListener failedListener) {
        fileUploader.addListener(failedListener);
    }

    @Override
    public void addListener(Upload.SucceededListener succeededListener) {
        fileUploader.addListener(succeededListener);
    }

    private void configureLayout(UploadType uploadType){

        this.uploadType = uploadType;
        setSizeUndefined();
    }

    private void initializeComponents(){

        fileUploader = FileUploaderFactory.getFileUploader(uploadType);
        payload = new ArrayList<Document>();

        //when each upload is finished, update the payload
        fileUploader.addListener(new Upload.SucceededListener() {
            @Override
            public void uploadSucceeded(Upload.SucceededEvent event) {
                if (uploadType == UploadType.SINGLE){
                    payload = new ArrayList<Document>();
                    payload.add((Document)fileUploader.getPayload());
                }
                else if (uploadType == UploadType.MULTIPLE) {
                    payload = (List<Document>)fileUploader.getPayload();
                }
            }
        });
    }

    private void drawContents(){

        addComponent(fileUploader);
        setComponentAlignment(fileUploader, Alignment.MIDDLE_CENTER);
    }
}
