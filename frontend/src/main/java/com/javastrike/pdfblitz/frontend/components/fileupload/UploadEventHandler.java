package com.javastrike.pdfblitz.frontend.components.fileupload;

import com.vaadin.ui.Upload;

/**
 * Components that wish to respond to upload events (such as a failed or succeded upload) should implement
 * this interface, while also keeping an instance of a FileUploader
 *
 * TODO: This interface should provide methods that do not depend on the Upload component from Vaadin
 * @see FileUploader
 * @see DocumentUploader
 * @see com.javastrike.pdfblitz.frontend.windows.FileUploadWindow
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
