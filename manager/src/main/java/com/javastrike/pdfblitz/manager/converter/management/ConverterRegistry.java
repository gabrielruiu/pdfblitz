package com.javastrike.pdfblitz.manager.converter.management;

import java.util.Set;

import com.javastrike.pdfblitz.manager.converter.Converter;
import com.javastrike.pdfblitz.manager.model.Document;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public interface ConverterRegistry {

    Set<Converter> getDocumentConverters(Class<? extends Document> documentType);

    Set<Converter> getDocumentConverters();

    void registerDocumentConverter(Converter converter);

    void removeDocumentConverter(Converter converter);
}
