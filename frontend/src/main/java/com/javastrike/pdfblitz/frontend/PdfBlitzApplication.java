package com.javastrike.pdfblitz.frontend;

import com.javastrike.pdfblitz.frontend.page.ContactPage;
import com.javastrike.pdfblitz.frontend.page.DashboardPage;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.settings.BootswatchThemeProvider;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class PdfBlitzApplication extends WebApplication {

    @Override
    protected void init() {
        super.init();
        configureMountPaths();
        configureSpring();
        configureBootstrap();
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return DashboardPage.class;
    }

    private void configureMountPaths() {
        mountPage("/contact", ContactPage.class);
        mountPage("/", DashboardPage.class);
    }

    private void configureSpring() {
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        //TODO: add Spring as a message provider (http://www.jroller.com/eyallupu/entry/spring_as_a_message_provider)
        // http://wicket.apache.org/guide/guide/i18n.html
    }

    private void configureBootstrap() {
        BootstrapSettings settings = new BootstrapSettings();
        settings.setThemeProvider(new BootswatchThemeProvider(BootswatchTheme.Flatly));
        Bootstrap.install(this, settings);
    }
}
