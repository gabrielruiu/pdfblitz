package com.javastrike.pdfblitz.frontend;

import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.frontend.pages.HomePage;
import com.javastrike.pdfblitz.frontend.theme.PdfBlitzTheme;
import com.vaadin.Application;
import com.vaadin.service.ApplicationContext;
import com.vaadin.ui.Window;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class PdfBlitzApplication extends Application implements ApplicationContext.TransactionListener{


    private static ThreadLocal<PdfBlitzApplication> currentApplication = new ThreadLocal<PdfBlitzApplication>();

    private Window mainWindow;
    private Document document;

    @Override
    public void init() {

        setInstance(this);
        if (getContext() != null) {
            getContext().addTransactionListener(this);
        }

        document = null;

        setTheme(PdfBlitzTheme.THEME_NAME);

        mainWindow = new Window("PdfBlitz");
        mainWindow.addComponent(new HomePage());

        setMainWindow(mainWindow);
    }

    public Document getDocument(){
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }


    /**
     * @return the current application instance
     */
    public static PdfBlitzApplication getInstance() {
        return currentApplication.get();
    }

    /**
     * Set the current application instance
     */
    public static void setInstance(PdfBlitzApplication application) {
        currentApplication.set(application);
    }

    /**
     * Remove the current application instance
     */
    public static void removeCurrent() {
        currentApplication.remove();
    }

    /**
     * TransactionListener
     */
    public void transactionStart(Application application, Object transactionData) {
        if (application == this) {
            PdfBlitzApplication.setInstance(this);
        }
    }

    public void transactionEnd(Application application, Object transactionData) {
        if (application == this) {
            // Remove locale from the executing thread
            removeCurrent();
        }
    }

    @Override
    public void close() {
        getContext().removeTransactionListener(this);
        super.close();
    }

}
