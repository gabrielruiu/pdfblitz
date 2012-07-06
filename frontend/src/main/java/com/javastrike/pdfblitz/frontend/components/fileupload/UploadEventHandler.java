package com.javastrike.pdfblitz.frontend.components.fileupload;

import com.vaadin.ui.Upload;

/**
 * Components that wish to respond to upload events ( such as a failed or finished upload ) should implement
 * this interface, while also keeping an instance of a FileUploader
 *
 * @see FileUploader
 *
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public interface UploadEventHandler {

    void addListener(Upload.StartedListener startedListener);

    void addListener(Upload.ProgressListener progressListener);

    void addListener(Upload.FinishedListener finishedListener);

    void addListener(Upload.FailedListener failedListener);

    void addListener(Upload.SucceededListener succeededListener);
}
