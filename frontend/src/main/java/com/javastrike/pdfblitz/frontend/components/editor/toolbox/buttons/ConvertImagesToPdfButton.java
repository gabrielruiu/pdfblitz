package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons;

import com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener.ConvertImagesToPdfClickListener;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ConvertImagesToPdfButton extends DocumentOperationButton {

    public ConvertImagesToPdfButton() {
        super("Convert several image files into a single PDF document", new ConvertImagesToPdfClickListener());
    }
}
