package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.DeletePagesClickListener;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DeletePagesButton extends DocumentOperationButton {


    public DeletePagesButton() {
        super("Delete pages from your PDF document", new DeletePagesClickListener());
    }
}
