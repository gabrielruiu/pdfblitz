package com.javastrike.pdfblitz.frontend;

import com.javastrike.pdfblitz.frontend.components.editor.DocumentToolbox;
import com.javastrike.pdfblitz.frontend.document.provider.StreamResourceConverter;
import com.javastrike.pdfblitz.frontend.theme.PdfBlitzTheme;
import com.javastrike.pdfblitz.manager.DocumentManager;
import com.vaadin.service.ApplicationContext;
import com.vaadin.ui.Window;
import eu.livotov.tpt.TPTApplication;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContextAware;

/**
 *  Main application class
 *
 *  It currently extends TPTApplication in order to benefit from the functionalities provided
 *  by the toolkit-productivity-tools package, such as the ThreadLocal pattern implementation
 *
 * @author Ruiu Gabriel Mihai
 */

@Configurable
public class PdfBlitzApplication extends TPTApplication implements ApplicationContext.TransactionListener,
        ApplicationContextAware {


    //TODO: inject using Spring
    @Autowired
    private DocumentManager documentManager;

    private org.springframework.context.ApplicationContext applicationContext;

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

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext)
            throws BeansException {

        this.applicationContext = applicationContext;
    }
}
