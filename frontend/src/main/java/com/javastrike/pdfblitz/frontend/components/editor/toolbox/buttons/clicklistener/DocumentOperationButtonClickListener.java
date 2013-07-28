package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener;

import com.javastrike.pdfblitz.frontend.PdfBlitzUI;
import com.javastrike.pdfblitz.frontend.components.fileupload.UploadType;
import com.javastrike.pdfblitz.frontend.document.FileStreamDownloadResource;
import com.javastrike.pdfblitz.frontend.utils.ArchiveUtils;
import com.javastrike.pdfblitz.frontend.windows.FileUploadWindow;
import com.javastrike.pdfblitz.frontend.zip.DefaultDocumentArchiver;
import com.javastrike.pdfblitz.frontend.zip.exception.ArchivingException;
import com.javastrike.pdfblitz.manager.exception.DocumentOperationException;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.exception.pdfoperations.EncryptedException;
import com.javastrike.pdfblitz.manager.exception.pdfoperations.PageIndicesOutOfRangeException;
import com.javastrike.pdfblitz.manager.exception.pdfoperations.PdfDocumentOperationException;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.operations.ConversionOperations;
import com.javastrike.pdfblitz.manager.operations.PdfDocumentOperations;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.*;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public abstract class DocumentOperationButtonClickListener implements Button.ClickListener {


    private static final Logger LOG = Logger.getLogger(DocumentOperationButtonClickListener.class);

    private PdfDocumentOperations pdfDocumentOperations;
    private ConversionOperations conversionOperations;
    private UploadType uploadType;
    private boolean requiresWindowForOperationConfiguration;
    private String windowName;

    private List<? extends Document> filesDownloaded;


    protected DocumentOperationButtonClickListener(UploadType uploadType,
                                                   boolean requiresWindowForOperationConfiguration, String windowName) {

        this.uploadType = uploadType;
        this.requiresWindowForOperationConfiguration = requiresWindowForOperationConfiguration;
        this.windowName = windowName;
        initDocumentOperations();
    }


    @Override
    public void buttonClick(Button.ClickEvent event) {
        openFileUploadWindowAndDownloadFiles();
    }

    protected PdfDocumentOperations getPdfDocumentOperations() {
        return pdfDocumentOperations;
    }

    protected ConversionOperations getConversionOperations() {
        return conversionOperations;
    }


    private void initDocumentOperations() {
        pdfDocumentOperations = ((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getDocumentManager().getDocumentOperations();

        conversionOperations =  ((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getDocumentManager().getConversionOperations();
    }

    private void openFileUploadWindowAndDownloadFiles() {

        filesDownloaded = new ArrayList<Document>();
        final FileUploadWindow fileUploadWindow = new FileUploadWindow(uploadType);

        PdfBlitzUI.getCurrent().addWindow(fileUploadWindow);

        if (uploadType == UploadType.MULTIPLE) {
            fileUploadWindow.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {

                    getDownloadedFilesFromFileUploadWindow(fileUploadWindow);
                    handleDocumentOperations();
                }
            });
        } else {
            fileUploadWindow.getDocumentUploader().addListener(new Upload.SucceededListener() {
                @Override
                public void uploadSucceeded(Upload.SucceededEvent event) {

                    getDownloadedFilesFromFileUploadWindow(fileUploadWindow);
                    handleDocumentOperations();
                }
            });
        }
    }



    private void openOperationWindow(){
       PdfBlitzUI.getCurrent().addWindow(new OperationWindow());
    }

    protected abstract List<? extends Document> performOperationOnFiles(List<? extends Document> documents)
            throws DocumentOperationException;

    protected abstract Layout getOperationInterface();

    private void handleDocumentOperations() {

        if (requiresWindowForOperationConfiguration){
            openOperationWindow();
        } else {
            processFiles();
        }
    }

    private void getDownloadedFilesFromFileUploadWindow(FileUploadWindow fileUploadWindow) {

        filesDownloaded = fileUploadWindow.getDocumentUploader().getPayload();
        PdfBlitzUI.getCurrent().removeWindow(fileUploadWindow);
    }

    private void processFiles() {

        List<? extends Document> modifiedDocuments = null;
        try {
            modifiedDocuments = performOperationOnFiles(filesDownloaded);
            archiveModifiedFilesAndSendForDownload(modifiedDocuments);
        } catch (DocumentOperationException e) {
            showError(e);
        }
    }

    //TODO: move to PdfBlitzUI?
    private void showError(DocumentOperationException operationException) {

        String errorMessage = "Unknown error occured";
        if (operationException instanceof ArchivingException){
            errorMessage = "An error has occured while archiving the documents";
        }
        if (operationException instanceof PdfDocumentOperationException) {
            errorMessage = "Error while performing the operation on the documents";
        }
        if (operationException instanceof ConversionException) {
            errorMessage = "Error performing conversion operation";
        }
        if (operationException instanceof EncryptedException){
            errorMessage = "Document is encrypted, must be decrypted before use";
        }
        if (operationException instanceof PageIndicesOutOfRangeException) {
            errorMessage = "The page indices are invalid (eg. out of range, invalid expression";
        }

        Notification.show(errorMessage, Notification.Type.ERROR_MESSAGE);
    }


    private void archiveModifiedFilesAndSendForDownload(List<? extends Document> modifiedDocuments)
            throws DocumentOperationException {

        try {

            final ByteArrayOutputStream archiveStream = new ByteArrayOutputStream();
            new DefaultDocumentArchiver().archive(modifiedDocuments,archiveStream);

            StreamResource.StreamSource streamSource = new StreamResource.StreamSource() {
                @Override
                public InputStream getStream() {
                    return new ByteArrayInputStream(archiveStream.toByteArray());
                }
            };

            PdfBlitzUI.getCurrent().getMainWindow().open(
                    new FileStreamDownloadResource(streamSource, ArchiveUtils.generateNameForArchive()));


        } catch (ArchivingException e) {
            LOG.error("Error archiving documents", e);
            throw new ArchivingException("Error archiving documents", e);
        }
    }

    //TODO: convinience class that closes windows without calling long static method from PdfBlitzUI
    private class OperationWindow extends Window {


        private OperationWindow() {

            super(windowName);

            setModal(true);
            center();
            setWidth(400, Unit.PIXELS);

            HorizontalLayout layout = new HorizontalLayout();

            layout.addComponent(getOperationInterface());
            layout.addComponent(new Button(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                    getMessage("documentoperation.button"), new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {

                    processFiles();
                    PdfBlitzUI.getCurrent().removeWindow(OperationWindow.this);
                }
            }));

            setContent(layout);
        }
    }
}
