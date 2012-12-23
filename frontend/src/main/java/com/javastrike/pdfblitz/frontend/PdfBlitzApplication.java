package com.javastrike.pdfblitz.frontend;

import com.javastrike.pdfblitz.frontend.components.editor.DocumentToolbox;
import com.javastrike.pdfblitz.frontend.theme.PdfBlitzTheme;
import com.javastrike.pdfblitz.manager.DocumentManager;
import com.vaadin.service.ApplicationContext;
import com.vaadin.ui.Window;
import eu.livotov.tpt.TPTApplication;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;

import java.util.Locale;

/**
 *  Main application class
 *
 *  It currently extends TPTApplication in order to benefit from the functionalities provided
 *  by the toolkit-productivity-tools package, such as the ThreadLocal pattern implementation
 *
 * @author Ruiu Gabriel Mihai
 */

@SuppressWarnings("serial")
public class PdfBlitzApplication extends TPTApplication implements ApplicationContext.TransactionListener,
        ApplicationContextAware {

    @Autowired
    private DocumentManager documentManager;

    private org.springframework.context.ApplicationContext applicationContext;

    public DocumentManager getDocumentManager() {
        return documentManager;
    }

    @Override
    public void applicationInit() {

        setTheme(PdfBlitzTheme.THEME_NAME);

        setLocale(Locale.ENGLISH);

        Window mainWindow = new Window("PdfBlitz");
        mainWindow.setContent(new DocumentToolbox());
        /*mainWindow.setContent(new TestLayout());*/
        /*mainWindow.setContent(new LocaleTestLayout());*/
        setMainWindow(mainWindow);
    }

    @Override
    public void firstApplicationStartup() {

    }

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext)
            throws BeansException {

        this.applicationContext = applicationContext;
    }

    public org.springframework.context.ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public String getMessage(String key, Object[] args) {
        return applicationContext.getMessage(key, args,
                "Key not found for format" + getLocale().getCountry(), getLocale());
    }
    public String getMessage(String key) {
        return applicationContext.getMessage(key, null,
                "Key not found for format: " + getLocale().getDisplayName(), getLocale());
    }
}
