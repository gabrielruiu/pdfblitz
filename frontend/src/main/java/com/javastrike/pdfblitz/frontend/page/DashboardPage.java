package com.javastrike.pdfblitz.frontend.page;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import org.apache.wicket.model.Model;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DashboardPage extends PdfBlitzPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();
        addButtons();
    }

    private void addButtons() {
        addPdfToImagesButton();
    }

    protected void addPdfToImagesButton() {
        add(new BootstrapButton("button-pdf-to-images", Model.of("Convert PDF to images"), Buttons.Type.Link));
    }
}
