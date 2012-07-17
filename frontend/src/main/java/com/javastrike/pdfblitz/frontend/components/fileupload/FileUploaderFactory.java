package com.javastrike.pdfblitz.frontend.components.fileupload;

/**
 * Generates a particular FileUploader component depending on the upload type needed.
 *
 * NOTE: Because of the difficulties of developing a proper component to upload multiple files at once,
 * I have settled, temporarily, for a component that uploads single files multiple times and shows a
 * list of the uploaded file names.
 *
 * @see DocumentUploader
 * @see FileUploader
 * @see UploadType
 *
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 *
 * TODO: manage using Spring
 */
public abstract class FileUploaderFactory {


    public static FileUploader getFileUploader(UploadType uploadType){

        FileUploader fileUploader = null;
        if (uploadType == UploadType.SINGLE){
            fileUploader = new SingleFileUploader();
        }
        else if (uploadType == UploadType.MULTIPLE){
            fileUploader = new MultiFileUploader();
        }
        return fileUploader;
    }
}
