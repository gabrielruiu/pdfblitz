package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.ConvertPdfToImagesClickListener;
import com.vaadin.terminal.ThemeResource;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ConvertPdfToImagesButton extends DocumentOperationButton {

    public ConvertPdfToImagesButton() {
        super(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getMessage("button.convert.pdftoimages"), new ConvertPdfToImagesClickListener(),
        		new ThemeResource("icons/operations/pdftoimage.png"));
    }
}
