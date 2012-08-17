package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.SplitPagesClickListener;
import com.vaadin.terminal.ThemeResource;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class SplitPagesButton extends DocumentOperationButton {


    public SplitPagesButton() {
        super("Split into several documents",new SplitPagesClickListener(),
        		new ThemeResource("icons/operations/split.png"));
    }
}
