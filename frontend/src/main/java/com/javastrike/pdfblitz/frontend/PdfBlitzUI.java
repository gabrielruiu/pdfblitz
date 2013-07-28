package com.javastrike.pdfblitz.frontend;

import com.javastrike.pdfblitz.frontend.components.editor.DocumentToolbox;
import com.javastrike.pdfblitz.frontend.theme.PdfBlitzTheme;
import com.javastrike.pdfblitz.manager.DocumentManager;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
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
@Theme(PdfBlitzTheme.THEME_NAME)
@PreserveOnRefresh
public class PdfBlitzUI extends UI implements ApplicationContextAware {

    @Autowired
    private DocumentManager documentManager;

    private org.springframework.context.ApplicationContext applicationContext;

    public DocumentManager getDocumentManager() {
        return documentManager;
    }

    @Override
    protected void init(VaadinRequest request) {

        setLocale(Locale.ENGLISH);
        getPage().setTitle("PdfBlitz");

        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        setContent(content);

        DocumentToolbox documentToolbox = new DocumentToolbox();
        content.addComponent(documentToolbox);
        content.setExpandRatio(documentToolbox, 1);
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
