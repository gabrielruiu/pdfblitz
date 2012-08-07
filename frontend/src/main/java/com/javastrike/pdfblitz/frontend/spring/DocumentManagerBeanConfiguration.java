package com.javastrike.pdfblitz.frontend.spring;

import com.javastrike.pdfblitz.frontend.document.provider.StreamResourceConverter;
import com.javastrike.pdfblitz.manager.DocumentManager;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration class
 * 
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@Configuration
public class DocumentManagerBeanConfiguration {

    @Bean(autowire = Autowire.BY_TYPE)
    public DocumentManager documentManager(){

        DocumentManager documentManager = new DocumentManager();
        documentManager.getConversionOperations().getConverterResolver()
                .getConverterRegistry().registerDocumentConverter(new StreamResourceConverter());
        return documentManager;
    }
}
