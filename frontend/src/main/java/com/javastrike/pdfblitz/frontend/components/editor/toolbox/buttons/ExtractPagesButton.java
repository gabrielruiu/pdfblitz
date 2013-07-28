package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.PdfBlitzUI;
import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.ExtractPagesClickListener;
import com.vaadin.server.ThemeResource;


/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ExtractPagesButton extends DocumentOperationButton {


    public ExtractPagesButton() {
        super(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getMessage("button.operation.extract"), new ExtractPagesClickListener(),
        		new ThemeResource("icons/operations/extract.png"));
    }
}
