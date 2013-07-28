package com.javastrike.pdfblitz.frontend.components.fileupload;

import com.javastrike.pdfblitz.frontend.PdfBlitzUI;
import com.javastrike.pdfblitz.manager.model.Document;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Upload;

import java.util.ArrayList;
import java.util.List;


/**
 * Component that handles multiple-file uploads.
 *
 * Essentially, its a {@link SingleFileUploader} that uploads files several times.
 * The user has to upload every single file separately
 *
 * @see DocumentUploader
 * @see UploadType
 * @see FileUploaderFactory
 * @see FileUploader
 *
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class MultiFileUploader extends FileUploader<List<Document>> {

    private List<Document> payload;
    private SingleFileUploader singleFileUploader;
    private ListSelect listOfUploadedFiles;


    public MultiFileUploader() {

        initializeComponents();
        configureLayout();
        drawContents();
    }

    @Override
    public List<Document> getPayload() {
        return payload;
    }

    @Override
    public void addListener(Upload.StartedListener startedListener) {
        singleFileUploader.addListener(startedListener);
    }

    @Override
    public void addListener(Upload.ProgressListener progressListener) {
        singleFileUploader.addListener(progressListener);
    }

    @Override
    public void addListener(Upload.FinishedListener finishedListener) {
        singleFileUploader.addListener(finishedListener);
    }

    @Override
    public void addListener(Upload.FailedListener failedListener) {
        singleFileUploader.addListener(failedListener);
    }

    @Override
    public void addListener(Upload.SucceededListener succeededListener) {
        singleFileUploader.addListener(succeededListener);
    }

    private void initializeComponents() {

        payload = new ArrayList<Document>();

        singleFileUploader = new SingleFileUploader();

        //each successful upload is inserted into the payload
        singleFileUploader.addListener(new Upload.SucceededListener() {
            @Override
            public void uploadSucceeded(Upload.SucceededEvent event) {

                Document uploadedFile = singleFileUploader.getPayload();
                payload.add(uploadedFile);
                listOfUploadedFiles.addItem(uploadedFile.getName());
            }
        });

        listOfUploadedFiles = new ListSelect(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getMessage("fileuploader.multiple.filelist.title"));
        listOfUploadedFiles.setNullSelectionAllowed(false);
        listOfUploadedFiles.setWidth(350, UNITS_PIXELS);
    }

    private void configureLayout() {
        setSizeUndefined();
    }

    private void drawContents() {

        addComponent(singleFileUploader);
        addComponent(listOfUploadedFiles);
    }
}
