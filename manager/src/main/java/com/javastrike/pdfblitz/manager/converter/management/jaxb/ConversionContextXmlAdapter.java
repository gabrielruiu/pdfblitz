package com.javastrike.pdfblitz.manager.converter.management.jaxb;

import com.javastrike.pdfblitz.manager.converter.management.ConversionParameter;
import com.javastrike.pdfblitz.manager.converter.management.IdentifierType;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ConversionContextXmlAdapter
        extends XmlAdapter<ConversionContextMap, Map<IdentifierType, ConversionParameter<?>>> {

    @Override
    public Map<IdentifierType, ConversionParameter<?>> unmarshal(ConversionContextMap v) throws Exception {

        Map<IdentifierType, ConversionParameter<?>> map = new HashMap<IdentifierType, ConversionParameter<?>>();
        for (ConversionContextEntry entry : v.getEntries()) {
            map.put(entry.getIdentifierType(), entry.getConversionParameter());
        }
        return map;
    }

    @Override
    public ConversionContextMap marshal(Map<IdentifierType, ConversionParameter<?>> v) throws Exception {

        ConversionContextMap map = new ConversionContextMap();
        List<ConversionContextEntry> entryList = new ArrayList<ConversionContextEntry>();
        for (Map.Entry<IdentifierType, ConversionParameter<?>> entry : v.entrySet()) {
            entryList.add(new ConversionContextEntry(entry.getKey(), entry.getValue()));
        }
        map.setEntries(entryList);
        return map;
    }
}
