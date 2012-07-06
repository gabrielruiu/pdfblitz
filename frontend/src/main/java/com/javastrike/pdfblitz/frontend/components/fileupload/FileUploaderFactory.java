package com.javastrike.pdfblitz.frontend.components.fileupload;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public abstract class FileUploaderFactory {


    public static FileUploader getFileUploader(UploadType uploadType){

        FileUploader fileUploader;
        if (uploadType == UploadType.SINGLE){
            fileUploader = new SingleFileUploader();
        }
        else{
            fileUploader = new MultiFileUploader();
        }
        return fileUploader;
    }
}
