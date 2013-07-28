package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.PdfBlitzUI;
import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.ConvertTextToPdfClickListener;
import com.vaadin.server.ThemeResource;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ConvertTextToPdfButton extends DocumentOperationButton {

    public ConvertTextToPdfButton() {
        super(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getMessage("button.convert.texttopdf"), new ConvertTextToPdfClickListener(),
                new ThemeResource("icons/operations/texttopdf.png"));
    }
}
