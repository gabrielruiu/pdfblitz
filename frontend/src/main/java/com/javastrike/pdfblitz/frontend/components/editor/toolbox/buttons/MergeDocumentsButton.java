package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.MergeDocumentsClickListener;
import com.vaadin.terminal.ThemeResource;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class MergeDocumentsButton extends DocumentOperationButton {

    public MergeDocumentsButton() {
        super(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getMessage("button.operation.merge"), new MergeDocumentsClickListener(),
        		new ThemeResource("icons/operations/merge.png"));
    }
}
