package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.ExtractPagesClickListener;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ExtractPagesButton extends DocumentOperationButton {


    public ExtractPagesButton() {
        super("Extract pages as individual documents", new ExtractPagesClickListener());
    }
}
