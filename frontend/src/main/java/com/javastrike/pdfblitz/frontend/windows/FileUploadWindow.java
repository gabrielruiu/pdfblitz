package com.javastrike.pdfblitz.frontend.windows;

import com.javastrike.pdfblitz.frontend.components.FileUploader;
import com.javastrike.pdfblitz.frontend.pages.DocumentEditorPage;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Window;
import org.vaadin.easyuploads.MultiFileUpload;

import java.io.File;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class FileUploadWindow extends Window{


    private FileUploader fileUploader;

    public FileUploadWindow() {

        super("Upload your file");
        configureWindow();
        initializeComponents();
        drawContents();
    }


    private void configureWindow(){

        setModal(true);
        center();
        setWidth("360px");
        setHeight("300px");
    }

    private void initializeComponents(){

        fileUploader = new FileUploader();
        fileUploader.addListener(new Upload.SucceededListener() {
            @Override
            public void uploadSucceeded(Upload.SucceededEvent event) {
                //TODO: get the actual payload from the FileUploader and put that in the DocumentEditorPage
/*                DocumentEditorPage documentEditorPage = new DocumentEditorPage(fileUploader.getPayload());
                getApplication().addWindow(documentEditorPage);
                open(new ExternalResource(documentEditorPage.getURL()));*/
                getApplication().getMainWindow().setContent(new DocumentEditorPage(fileUploader.getPayload()));
                close();
            }
        });
        fileUploader.addListener(new Upload.FailedListener() {
            @Override
            public void uploadFailed(Upload.FailedEvent event) {
                //TODO: show failure notification
            }
        });
    }

    private void drawContents(){
        /*addComponent(fileUploader);*/
        addComponent(new MultiFileUpload() {
            @Override
            protected void handleFile(File file, String s, String s1, long l) {
                String msg = s + " uploaded.";
                getWindow().showNotification(msg);

            }
        });
    }
}
