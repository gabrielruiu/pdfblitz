package com.javastrike.pdfblitz.frontend;

import com.javastrike.pdfblitz.frontend.pages.HomePage;
import com.javastrike.pdfblitz.frontend.theme.PdfBlitzTheme;
import com.javastrike.pdfblitz.manager.DocumentManager;
import com.vaadin.service.ApplicationContext;
import eu.livotov.tpt.TPTApplication;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DocumentManager documentManager;

    public DocumentManager getDocumentManager() {
        return documentManager;
    }

    public void setDocumentManager(DocumentManager documentManager) {
        this.documentManager = documentManager;
    }

    @Override
    public void applicationInit() {

        setTheme(PdfBlitzTheme.THEME_NAME);

        documentManager = new DocumentManager();
        /*setupDocumentManager();*/

        setMainWindow(new HomePage());
    }

    @Override
    public void firstApplicationStartup() {

    }

/*    private void setupDocumentManager(){
        documentManager.registerDocumentProvider(new StreamResourceProvider());
    }*/
}
