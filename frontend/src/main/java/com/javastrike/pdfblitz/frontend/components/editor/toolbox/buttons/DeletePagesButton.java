package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.DeletePagesClickListener;
import com.vaadin.terminal.ThemeResource;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DeletePagesButton extends DocumentOperationButton {


    public DeletePagesButton() {
        super(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getMessage("button.operation.delete"), new DeletePagesClickListener(),
        		new ThemeResource("icons/operations/deletepages.png"));
    }
}
