package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.PdfBlitzUI;
import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.ConvertPdfToImagesClickListener;
import com.vaadin.server.ThemeResource;


/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ConvertPdfToImagesButton extends DocumentOperationButton {

    public ConvertPdfToImagesButton() {
        super(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getMessage("button.convert.pdftoimages"), new ConvertPdfToImagesClickListener(),
        		new ThemeResource("icons/operations/pdftoimage.png"));
    }
}
