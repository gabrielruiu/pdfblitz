package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.DeletePagesClickListener;
import com.vaadin.terminal.ThemeResource;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DeletePagesButton extends DocumentOperationButton {


    public DeletePagesButton() {
        super("Delete pages from your PDF document", new DeletePagesClickListener(),
        		new ThemeResource("icons/operations/deletepages.png"));
    }
}
