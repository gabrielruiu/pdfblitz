package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.PdfBlitzUI;
import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.SplitPagesClickListener;
import com.vaadin.server.ThemeResource;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class SplitPagesButton extends DocumentOperationButton {


    public SplitPagesButton() {
        super(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getMessage("button.operation.split"),new SplitPagesClickListener(),
        		new ThemeResource("icons/operations/split.png"));
    }
}
