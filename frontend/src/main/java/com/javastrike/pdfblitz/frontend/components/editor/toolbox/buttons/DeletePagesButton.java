package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.PdfBlitzUI;
import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.DeletePagesClickListener;
import com.vaadin.server.ThemeResource;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DeletePagesButton extends DocumentOperationButton {


    public DeletePagesButton() {
        super(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getMessage("button.operation.delete"), new DeletePagesClickListener(),
        		new ThemeResource("icons/operations/deletepages.png"));
    }
}
