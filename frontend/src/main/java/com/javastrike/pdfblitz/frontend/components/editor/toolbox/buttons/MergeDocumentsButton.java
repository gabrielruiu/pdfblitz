package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.MergeDocumentsClickListener;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class MergeDocumentsButton extends DocumentOperationButton {

    public MergeDocumentsButton() {
        super("Merge several PDF documents into a single file", new MergeDocumentsClickListener());
    }
}
