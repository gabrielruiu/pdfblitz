package com.javastrike.pdfblitz.frontend;

import com.javastrike.pdfblitz.frontend.config.ActiveProfile;
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
    private ActiveProfile activeProfile;

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
        this.activeProfile = ActiveProfile.valueOf(applicationContext.getEnvironment().getProperty("spring.profiles.active", "dev").toUpperCase());
    }

    public ActiveProfile getActiveProfile() {
        return activeProfile;
    }

    private void mountResources() {
        mountResource("/robots.txt", new PackageResourceReference(PdfBlitzApplication.class, "properties/" + activeProfile.name().toLowerCase() + "/robots.txt"));
    }

    private void configureMountPaths() {
        mountPage("/contact", ContactPage.class);
        mountPage("/", DashboardPage.class);
    }

    private void configureSpring() {
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));
    }

    private void configureBootstrap() {
        BootstrapSettings settings = new BootstrapSettings();
        settings.setThemeProvider(new BootswatchThemeProvider(BootswatchTheme.Flatly));
        Bootstrap.install(this, settings);
    }
}
