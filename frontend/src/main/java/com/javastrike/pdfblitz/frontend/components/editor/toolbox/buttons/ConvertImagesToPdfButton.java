package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.PdfBlitzUI;
import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.ConvertImagesToPdfClickListener;
import com.vaadin.server.ThemeResource;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ConvertImagesToPdfButton extends DocumentOperationButton {

    public ConvertImagesToPdfButton() {
        super(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getMessage("button.convert.imagestopdf"), new ConvertImagesToPdfClickListener(),
        		new ThemeResource("icons/operations/imagetopdf.png"));
    }
}
