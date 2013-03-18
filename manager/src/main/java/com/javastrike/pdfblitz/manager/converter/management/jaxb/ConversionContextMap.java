package com.javastrike.pdfblitz.manager.converter.management.jaxb;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ConversionContextMap {

    private List<ConversionContextEntry> entries;

    public List<ConversionContextEntry> getEntries() {
        if (entries == null) {
            entries = new ArrayList<ConversionContextEntry>();
        }
        return entries;
    }

    public void setEntries(List<ConversionContextEntry> entries) {
        this.entries = entries;
    }


}
