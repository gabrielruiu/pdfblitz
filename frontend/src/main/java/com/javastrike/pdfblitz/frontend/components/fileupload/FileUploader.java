package com.javastrike.pdfblitz.frontend.components.fileupload;

import com.vaadin.ui.VerticalLayout;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@SuppressWarnings("serial")
public abstract class FileUploader<Payload> extends VerticalLayout implements UploadEventHandler {

    public abstract Payload getPayload();
}
