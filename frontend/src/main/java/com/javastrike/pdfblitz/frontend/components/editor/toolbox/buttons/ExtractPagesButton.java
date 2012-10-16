package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.ExtractPagesClickListener;
import com.vaadin.terminal.ThemeResource;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ExtractPagesButton extends DocumentOperationButton {


    public ExtractPagesButton() {
        super(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getMessage("button.operation.extract"), new ExtractPagesClickListener(),
        		new ThemeResource("icons/operations/extract.png"));
    }
}
