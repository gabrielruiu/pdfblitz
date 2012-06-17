package com.javastrike.pdfblitz.frontend;

import com.javastrike.pdfblitz.frontend.pages.HomePage;
import com.javastrike.pdfblitz.frontend.provider.StreamResourceProvider;
import com.javastrike.pdfblitz.frontend.theme.PdfBlitzTheme;
import com.javastrike.pdfblitz.manager.DocumentManager;
import com.javastrike.pdfblitz.manager.model.Document;
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


    private DocumentManager documentManager;
    private Window mainWindow;

    public DocumentManager getDocumentManager() {
        return documentManager;
    }

    public void setDocumentManager(DocumentManager documentManager) {
        this.documentManager = documentManager;
    }

    public void setDocument(Document document){
        documentManager.setDocument(document);
    }

    public Document getDocument(){
        return documentManager.getDocument();
    }

    @Override
    public void applicationInit() {

        setTheme(PdfBlitzTheme.THEME_NAME);

        documentManager = new DocumentManager();
        setupDocumentManager();

        mainWindow = new Window("PdfBlitz");
        mainWindow.addComponent(new HomePage());

        setMainWindow(mainWindow);
    }

    @Override
    public void firstApplicationStartup() {

    }

    private void setupDocumentManager(){
        documentManager.registerDocumentProvider(new StreamResourceProvider());
    }
}
