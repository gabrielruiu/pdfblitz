package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.PdfBlitzUI;
import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.MergeDocumentsClickListener;
import com.vaadin.server.ThemeResource;


/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class MergeDocumentsButton extends DocumentOperationButton {

    public MergeDocumentsButton() {
        super(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getMessage("button.operation.merge"), new MergeDocumentsClickListener(),
        		new ThemeResource("icons/operations/merge.png"));
    }
}
