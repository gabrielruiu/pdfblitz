package com.javastrike.pdfblitz.frontend.windows;

import com.javastrike.pdfblitz.frontend.components.fileupload.DocumentUploader;
import com.javastrike.pdfblitz.frontend.components.fileupload.UploadType;
import com.javastrike.pdfblitz.frontend.pages.DocumentEditorPage;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Window;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@SuppressWarnings("serial")
public class FileUploadWindow extends Window{


    private DocumentUploader documentUploader;
    private UploadType uploadType;

    public FileUploadWindow(UploadType uploadType) {

        super("Upload your file");
        configureWindow(uploadType);
        initializeComponents();
        drawContents();
    }


    private void configureWindow(UploadType uploadType){

        this.uploadType = uploadType;
        setModal(true);
        center();
        setWidth(400,UNITS_PIXELS);
    }

    private void initializeComponents(){

        documentUploader = new DocumentUploader(uploadType);
        documentUploader.addListener(new Upload.SucceededListener() {
            @Override
            public void uploadSucceeded(Upload.SucceededEvent event) {
                //TODO: expose UploadEventHandler in this window, instead of hardcode
                DocumentEditorPage editorPage = new DocumentEditorPage(documentUploader.getPayload().get(0));

                close();
            }
        });
        documentUploader.addListener(new Upload.FailedListener() {
            @Override
            public void uploadFailed(Upload.FailedEvent event) {
                showNotification("Failed to upload file(s)",Notification.TYPE_ERROR_MESSAGE);
            }
        });
    }

    private void drawContents(){
        addComponent(documentUploader);
    }
}
