package com.javastrike.pdfblitz.frontend.windows;

import com.javastrike.pdfblitz.frontend.PdfBlitzUI;
import com.javastrike.pdfblitz.frontend.components.fileupload.DocumentUploader;
import com.javastrike.pdfblitz.frontend.components.fileupload.UploadType;
import com.vaadin.ui.*;

/**
 * Modal window that wraps the DocumentUploader component.
 *
 * NOTE: Because of the difficulties of developing a proper component to upload multiple files at once,
 * I have settled, temporarily, for a component that uploads single files multiple times and shows a
 * list of the uploaded file names.
 *
 * When the user is done uploading all files, a button is provided to finish the process.
 * The buttons behavior is managed through the
 * {@link #addClickListener(com.vaadin.ui.Button.ClickListener)} method.
 *
 * @see DocumentUploader
 * @see UploadType
 *
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@SuppressWarnings("serial")
public class FileUploadWindow extends Window {


    private DocumentUploader documentUploader;
    private UploadType uploadType;
    private Button finishedWithUploads;

    public FileUploadWindow(UploadType uploadType) {

        super(((PdfBlitzUI) PdfBlitzUI.getCurrent()).getMessage("fileuploadwindow.title"));
        configureWindow(uploadType);
        initializeComponents();
        drawContents();
    }


    public DocumentUploader getDocumentUploader(){
        return documentUploader;
    }

    public void addClickListener(Button.ClickListener clickListener){

        if(uploadType == UploadType.MULTIPLE){
            finishedWithUploads.addListener(clickListener);
        }
    }

    private void configureWindow(UploadType uploadType){

        this.uploadType = uploadType;
        setModal(true);
        center();
        setWidth(400, Unit.PIXELS);
    }

    private void initializeComponents(){

        documentUploader = new DocumentUploader(uploadType);

        documentUploader.resetPayload();

        if (uploadType == UploadType.MULTIPLE) {

            finishedWithUploads = new Button(
                    ((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                            getMessage("fileuploader.multiple.button.finish"));
            documentUploader.addComponent(finishedWithUploads);
            documentUploader.setComponentAlignment(finishedWithUploads, Alignment.MIDDLE_CENTER);
        }
        //TODO: bring all error notification messages into one place
        documentUploader.addListener(new Upload.FailedListener() {
            @Override
            public void uploadFailed(Upload.FailedEvent event) {
                Notification.show(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                        getMessage("notification.fileupload.failed"), Notification.Type.ERROR_MESSAGE);
            }
        });
    }

    private void drawContents(){
        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(documentUploader);
        setContent(layout);
    }
}
