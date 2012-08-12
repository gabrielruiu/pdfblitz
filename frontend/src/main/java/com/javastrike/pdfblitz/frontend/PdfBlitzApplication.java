package com.javastrike.pdfblitz.frontend;

import com.javastrike.pdfblitz.frontend.components.editor.DocumentToolbox;
import com.javastrike.pdfblitz.frontend.document.provider.StreamResourceConverter;
import com.javastrike.pdfblitz.frontend.theme.PdfBlitzTheme;
import com.javastrike.pdfblitz.manager.DocumentManager;
import com.vaadin.service.ApplicationContext;
import com.vaadin.ui.Window;
import eu.livotov.tpt.TPTApplication;
import org.springframework.beans.factory.annotation.Configurable;

/**
 *  Main application class
 *
 *  It currently extends TPTApplication in order to benefit from the functionalities provided
 *  by the toolkit-productivity-tools package, such as the ThreadLocal pattern implementation
 *
 * @author Ruiu Gabriel Mihai
 */

@Configurable
public class PdfBlitzApplication extends TPTApplication implements ApplicationContext.TransactionListener{


    //TODO: inject using Spring
    private DocumentManager documentManager;

    public DocumentManager getDocumentManager() {
        return documentManager;
    }

    @Override
    public void applicationInit() {

        setTheme(PdfBlitzTheme.THEME_NAME);

        documentManager = new DocumentManager();
        setupDocumentManager();


        Window mainWindow = new Window("PdfBlitz");
        mainWindow.setContent(new DocumentToolbox());
        /*mainWindow.setContent(new TestLayout());*/
        setMainWindow(mainWindow);
    }

    @Override
    public void firstApplicationStartup() {

    }

    //TODO: remove when DocumentManager will be injected through Spring
    private void setupDocumentManager(){

        documentManager = new DocumentManager();
        documentManager.getConversionOperations().getConverterResolver()
                .getConverterRegistry().registerDocumentConverter((new StreamResourceConverter()));
    }
}
