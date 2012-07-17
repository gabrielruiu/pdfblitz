package com.javastrike.pdfblitz.manager;

import java.util.HashSet;
import java.util.Set;

import com.javastrike.pdfblitz.manager.converter.AdditionalConversionData;
import com.javastrike.pdfblitz.manager.converter.DocumentConverter;
import com.javastrike.pdfblitz.manager.converter.DocumentFileProvider;
import com.javastrike.pdfblitz.manager.converter.DocumentStreamProvider;
import com.javastrike.pdfblitz.manager.exception.ConversionException;
import com.javastrike.pdfblitz.manager.exception.UnsupportedConversionType;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.operations.DocumentOperations;

//TODO: UNDO functionality?
public class DocumentManager {


    private DocumentOperations documentOperations;
    private Set<DocumentConverter> documentConverters;

    public DocumentManager() {

        documentConverters = new HashSet<DocumentConverter>();
        registerDefaultProviders();
    }

    private void registerDefaultProviders(){

        registerDocumentConverter(new DocumentFileProvider());
        registerDocumentConverter(new DocumentStreamProvider());
    }

    public void registerDocumentConverter(DocumentConverter provider){
        documentConverters.add(provider);
    }

    public void removeDocumentConverter(DocumentConverter provider){
        documentConverters.remove(provider);
    }

    public Object convertFromDocument(Document document, Class clazz) throws ConversionException, UnsupportedConversionType{

        for (DocumentConverter converter : documentConverters){
            if (converter.supports(clazz)){
                return converter.provideDocument(document);
            }
        }
        throw new UnsupportedConversionType("Conversion into type " + clazz.getName() + " is not supported");
    }
    
    public Document convertToDocument(Object object, AdditionalConversionData additionalData) throws ConversionException, UnsupportedConversionType {
    	
    	for (DocumentConverter converter : documentConverters){
            if (converter.supports(object.getClass())){
                return converter.convertToDocument(object, additionalData);
            }
        }
        throw new UnsupportedConversionType("Conversion from type " + object.getClass().getName() + " is not supported");
    }
}
