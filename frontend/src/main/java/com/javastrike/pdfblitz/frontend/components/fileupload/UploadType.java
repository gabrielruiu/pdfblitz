package com.javastrike.pdfblitz.frontend.components.fileupload;

/**
 * What kind of upload component should be generated within the DocumentUploader
 * by the FileUploaderFactory
 *
 * NOTE: Because of the difficulties of developing a proper component to upload multiple files at once,
 * I have settled, temporarily, for a component that uploads single files multiple times and shows a
 * list of the uploaded file names.
 *
 * @see DocumentUploader
 * @see FileUploaderFactory
 *
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public enum UploadType {
    SINGLE,
    MULTIPLE
}
