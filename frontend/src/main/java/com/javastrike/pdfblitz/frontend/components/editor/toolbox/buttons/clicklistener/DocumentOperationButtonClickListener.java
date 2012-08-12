package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.frontend.components.fileupload.UploadType;
import com.javastrike.pdfblitz.frontend.document.FileDownloadResource;
import com.javastrike.pdfblitz.frontend.utils.ArchiveUtils;
import com.javastrike.pdfblitz.frontend.windows.FileUploadWindow;
import com.javastrike.pdfblitz.frontend.zip.DocumentToFileArchiver;
import com.javastrike.pdfblitz.frontend.zip.exception.CompressionException;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.operations.ConversionOperations;
import com.javastrike.pdfblitz.manager.operations.PdfDocumentOperations;
import com.vaadin.ui.Button;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Window;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.MessageFormat;
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
        pdfDocumentOperations = ((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getDocumentManager().getDocumentOperations();

        conversionOperations =  ((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getDocumentManager().getConversionOperations();
    }

    private void openFileUploadWindowAndDownloadFiles() {

        filesDownloaded = new ArrayList<Document>();
        final FileUploadWindow fileUploadWindow = new FileUploadWindow(uploadType);

        ((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).getMainWindow().addWindow(fileUploadWindow);

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

        ((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).getMainWindow().addWindow(
                new OperationWindow());
    }

    protected abstract List<? extends Document> performOperationOnFiles(List<? extends Document> documents);

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
        ((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).getMainWindow().
                removeWindow(fileUploadWindow);
    }

    private void processFiles() {

        List<? extends Document> modifiedDocuments = performOperationOnFiles(filesDownloaded);
        archiveModifiedFilesAndSendForDownload(modifiedDocuments);
    }


    //TODO: show error notifications
    private void archiveModifiedFilesAndSendForDownload(List<? extends Document> modifiedDocuments) {

        //TODO: get from ArchiveUtils.generateFullPathForArchive()
        //TODO: dont use "\\" character
        String path = MessageFormat.format("{0}{1}{2}",
                PdfBlitzApplication.getCurrentApplication().getContext().getBaseDirectory().getAbsolutePath(),
                "\\" + "VAADIN" + "\\" + "export" + "\\",
                ArchiveUtils.generateNameForArchive());

        File archiveFile = new File(path);

        try {
            new DocumentToFileArchiver().archive(modifiedDocuments,new FileOutputStream(archiveFile));
            ((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).getMainWindow().open(
                    new FileDownloadResource(archiveFile,PdfBlitzApplication.getCurrentApplication()));



        } catch (FileNotFoundException e) {
            LOG.error("Error archiving documents", e);
            /*showNotification("Error archiving documents", Window.Notification.TYPE_ERROR_MESSAGE);*/
        } catch (CompressionException e) {
            LOG.error("Error archiving documents", e);
            /*showNotification("Error archiving documents", Window.Notification.TYPE_ERROR_MESSAGE);*/
        }
    }

    //TODO: convinience class that closes windows without calling long static method from PdfBlitzApplication
    private class OperationWindow extends Window {


        private OperationWindow() {

            super(windowName);

            setModal(true);
            center();
            setWidth(400, UNITS_PIXELS);

            addComponent(getOperationInterface());
            addComponent(new Button("Perform operation and download file",new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {

                    processFiles();
                    ((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).getMainWindow().
                            removeWindow(OperationWindow.this);
                }
            }));
        }
    }
}
