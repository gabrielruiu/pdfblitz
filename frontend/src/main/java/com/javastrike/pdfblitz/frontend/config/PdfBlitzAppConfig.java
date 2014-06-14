package com.javastrike.pdfblitz.frontend.config;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@Configuration
@PropertySource("classpath:com/javastrike/pdfblitz/frontend/properties/${spring.profiles.active:dev}/application.properties")
public class PdfBlitzAppConfig {

    @Bean
    public PdfBlitzApplication pdfBlitzApplication() {
        return new PdfBlitzApplication();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("locale/messages");
        return messageSource;
    }
}
