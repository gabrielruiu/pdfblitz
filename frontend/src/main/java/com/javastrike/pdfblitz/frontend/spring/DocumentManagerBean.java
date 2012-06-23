package com.javastrike.pdfblitz.frontend.spring;

import com.javastrike.pdfblitz.frontend.provider.StreamResourceProvider;
import com.javastrike.pdfblitz.manager.DocumentManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@Configuration
public class DocumentManagerBean {

    @Bean
    public DocumentManager documentManager(){

        DocumentManager documentManager = new DocumentManager();
        documentManager.registerDocumentProvider(new StreamResourceProvider());
        return documentManager;
    }
}
