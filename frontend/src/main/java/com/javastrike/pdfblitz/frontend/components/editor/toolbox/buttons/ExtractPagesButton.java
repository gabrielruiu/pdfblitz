package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.ExtractPagesClickListener;
import com.vaadin.terminal.ThemeResource;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ExtractPagesButton extends DocumentOperationButton {


    public ExtractPagesButton() {
        super("Extract pages as individual documents", new ExtractPagesClickListener(),
        		new ThemeResource("icons/operations/extract.png"));
    }
}
