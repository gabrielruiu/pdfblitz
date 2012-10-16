package com.javastrike.pdfblitz.frontend.components.locale;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.frontend.locale.SupportedLocales;
import com.vaadin.data.Property;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ComboBox;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class LocaleSelector extends ComboBox {

    public LocaleSelector() {

        super(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getMessage("localeselector.caption"), SupportedLocales.getAsContainer());

        setWidth(110, UNITS_PIXELS);
        setImmediate(true);
        //calls the toString() of the SupportedLocales item
        setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_PROPERTY);
        setItemCaptionPropertyId("locale");
        setNullSelectionAllowed(false);
        setValue(SupportedLocales.valueOf(PdfBlitzApplication.getCurrentApplication().getLocale()));



        addListener(new LocaleSelectorValueChangeListener());
    }


    private class LocaleSelectorValueChangeListener implements ValueChangeListener {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {

            Property selectedLocaleProperty = event.getProperty();
            SupportedLocales selectedLocale = (SupportedLocales) selectedLocaleProperty.getValue();

            if (selectedLocale.getLocale() != PdfBlitzApplication.getCurrentApplication().getLocale()) {
                PdfBlitzApplication.getCurrentApplication().setLocale(selectedLocale.getLocale());
                PdfBlitzApplication.getCurrentApplication().getMainWindow().requestRepaint();
            }
        }
    }

}
