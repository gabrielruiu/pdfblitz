package com.javastrike.pdfblitz.frontend.locale;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public enum SupportedLocales{

    GERMANY(Locale.GERMANY),
    UK(Locale.UK),
    FRANCE(Locale.FRANCE),
    SPAIN(new Locale("es","ES")),
    ROMANIA(new Locale("ro","RO"));


    private Locale locale;


    private SupportedLocales(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }



    public static List<SupportedLocales> getAsList() {

        List<SupportedLocales> supportedLocaleses = new ArrayList<SupportedLocales>();
        for (SupportedLocales locale : values()) {
            supportedLocaleses.add(locale);
        }
        return Collections.unmodifiableList(supportedLocaleses);
    }

    public static Container getAsContainer() {

        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("localeName", String.class, null);

        for (SupportedLocales supportedLocale : values()){

            Item localeItem = container.addItem(supportedLocale);
            localeItem.getItemProperty("localeName").setValue(supportedLocale.toString());
            container.addItem(localeItem);
        }
        return container;
    }

    @Override
    public String toString() {
        return locale.getDisplayName();
    }

    public static SupportedLocales valueOf(Locale locale) {

        for (SupportedLocales supportedLocale : values()) {
            if (supportedLocale.getLocale() == locale){
                return supportedLocale;
            }
        }
        return null;
    }
}
