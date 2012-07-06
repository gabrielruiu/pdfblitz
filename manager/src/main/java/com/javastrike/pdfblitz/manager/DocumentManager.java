package com.javastrike.pdfblitz.manager;

import com.javastrike.pdfblitz.manager.converter.DocumentFileProvider;
import com.javastrike.pdfblitz.manager.converter.DocumentProvider;
import com.javastrike.pdfblitz.manager.exception.ConversionException;
import com.javastrike.pdfblitz.manager.exception.UnsupportedConversionType;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.operations.DocumentOperations;
import com.javastrike.pdfblitz.manager.provider.DocumentStreamProvider;

import java.util.HashSet;
import java.util.Set;

//TODO: UNDO functionality?
public class DocumentManager {


    private DocumentOperations documentOperations;
    private Set<DocumentProvider> documentProviders;

    public DocumentManager() {

        documentProviders = new HashSet<DocumentProvider>();
        registerDefaultProviders();
    }

    private void registerDefaultProviders(){

        registerDocumentProvider(new DocumentFileProvider());
        registerDocumentProvider(new DocumentStreamProvider());
    }

    public void registerDocumentProvider(DocumentProvider provider){
        documentProviders.add(provider);
    }

    public void removeDocumentProvider(DocumentProvider provider){
        documentProviders.remove(provider);
    }

    public Object getDocument(Document document, Class clazz) throws ConversionException, UnsupportedConversionType{

        for (DocumentProvider provider : documentProviders){
            if (provider.supports(clazz)){
                return provider.provideDocument(document);
            }
        }
        throw new UnsupportedConversionType("Conversion into type " + clazz.getName() + " is not supported");
    }
}
