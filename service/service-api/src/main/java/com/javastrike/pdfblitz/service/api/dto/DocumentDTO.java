package com.javastrike.pdfblitz.service.api.dto;

import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.model.Document;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@1and1.ro)
 */
@XmlRootElement
public class DocumentDTO<T extends Document> {

    private List<T> documents;
    private ConversionContext conversionContext;

    public List<T> getDocuments() {
        return documents;
    }

    public void setDocuments(List<T> documents) {
        this.documents = documents;
    }

    public ConversionContext getConversionContext() {
        return conversionContext;
    }

    public void setConversionContext(ConversionContext conversionContext) {
        this.conversionContext = conversionContext;
    }
}
