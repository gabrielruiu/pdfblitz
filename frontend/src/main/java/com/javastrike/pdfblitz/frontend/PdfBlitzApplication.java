package com.javastrike.pdfblitz.frontend;

import com.javastrike.pdfblitz.frontend.page.ContactPage;
import com.javastrike.pdfblitz.frontend.page.DashboardPage;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.settings.BootswatchThemeProvider;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class PdfBlitzApplication extends WebApplication implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    protected void init() {
        super.init();
        configureSpring();
        configureMountPaths();
        mountResources();
        configureBootstrap();
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return DashboardPage.class;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private void mountResources() {
        String currentProfile = applicationContext.getEnvironment().getProperty("spring.profiles.active", "dev");
        mountResource("/robots.txt", new PackageResourceReference(PdfBlitzApplication.class, "properties/" + currentProfile + "/robots.txt"));
    }

    private void configureMountPaths() {
        mountPage("/contact", ContactPage.class);
        mountPage("/", DashboardPage.class);
    }

    private void configureSpring() {
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));
        //TODO: add Spring as a message provider (http://www.jroller.com/eyallupu/entry/spring_as_a_message_provider)
        // http://wicket.apache.org/guide/guide/i18n.html
    }

    private void configureBootstrap() {
        BootstrapSettings settings = new BootstrapSettings();
        settings.setThemeProvider(new BootswatchThemeProvider(BootswatchTheme.Flatly));
        Bootstrap.install(this, settings);
    }
}
