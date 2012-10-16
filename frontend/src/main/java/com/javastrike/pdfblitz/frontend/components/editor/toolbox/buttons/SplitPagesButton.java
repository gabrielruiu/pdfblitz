package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.SplitPagesClickListener;
import com.vaadin.terminal.ThemeResource;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class SplitPagesButton extends DocumentOperationButton {


    public SplitPagesButton() {
        super(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getMessage("button.operation.split"),new SplitPagesClickListener(),
        		new ThemeResource("icons/operations/split.png"));
    }
}
