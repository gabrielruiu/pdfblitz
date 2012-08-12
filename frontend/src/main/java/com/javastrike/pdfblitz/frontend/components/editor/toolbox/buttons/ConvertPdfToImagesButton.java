package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.ConvertPdfToImagesClickListener;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ConvertPdfToImagesButton extends DocumentOperationButton {

    public ConvertPdfToImagesButton() {
        super("Convert a PDF document into several images ", new ConvertPdfToImagesClickListener());
    }
}
