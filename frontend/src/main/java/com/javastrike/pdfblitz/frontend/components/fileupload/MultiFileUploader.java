package com.javastrike.pdfblitz.frontend.components.fileupload;

import com.javastrike.pdfblitz.manager.model.Document;
import com.vaadin.ui.Upload;
import org.vaadin.easyuploads.FileBuffer;
import org.vaadin.easyuploads.FileFactory;
import org.vaadin.easyuploads.MultiFileUpload;

import java.io.File;
import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@SuppressWarnings("serial")
public class MultiFileUploader extends FileUploader<List<Document>>{


    private MultiFileUpload multiFileUpload;

    public MultiFileUploader() {

        configureLayout();
        initializeComponents();
        drawContents();
    }

    @Override
    public List<Document> getPayload() {
        return null;
    }

    @Override
    public void addListener(Upload.StartedListener startedListener) {

    }

    @Override
    public void addListener(Upload.ProgressListener progressListener) {

    }

    @Override
    public void addListener(Upload.FinishedListener finishedListener) {

    }

    @Override
    public void addListener(Upload.FailedListener failedListener) {

    }

    @Override
    public void addListener(Upload.SucceededListener succeededListener) {

    }

    private void configureLayout(){

    }

    private void initializeComponents(){

    }

    private void drawContents(){

    }

    private class MultiFileUploadBuffer extends FileBuffer{

        private MultiFileFactory factory;

        @Override
        public FileFactory getFileFactory() {

            if (factory == null){
                factory = new MultiFileFactory();
            }
            return factory;
        }
    }

    private class MultiFileFactory implements FileFactory{

        @Override
        public File createFile(String s, String s1) {
            return null;
        }
    }
}
