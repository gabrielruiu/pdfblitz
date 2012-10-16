package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.DocumentOperationButtonClickListener;
import com.javastrike.pdfblitz.frontend.theme.PdfBlitzTheme;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public abstract class DocumentOperationButton extends Button {


    protected DocumentOperationButton(String caption, DocumentOperationButtonClickListener listener, ThemeResource icon) {

        super(caption, listener);
        if (icon != null) {
            setIcon(icon);
        }
        addStyleName(PdfBlitzTheme.BUTTON_OPERATION);
    }

    protected DocumentOperationButton(String caption, DocumentOperationButtonClickListener listener) {
        this(caption, listener, null);
    }
}
