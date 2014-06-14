package com.javastrike.pdfblitz.frontend.page;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.frontend.config.ActiveProfile;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.ChromeFrameMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.MetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.OptimizedMobileViewportMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.ContainerBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.INavbarComponent;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.ImmutableNavbarComponent;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCssReference;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.util.template.PackageTextTemplate;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class PdfBlitzPage extends WebPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new ContainerBehavior());
        add(generateHtmlTag());
        generateMetaTags();
        generateNavbar();
    }

    protected HtmlTag generateHtmlTag() {
        return new HtmlTag("html", getLocale(), true);
    }

    protected void generateMetaTags() {
        add(new OptimizedMobileViewportMetaTag("viewport"));
        add(new ChromeFrameMetaTag("chrome-frame"));
        add(new MetaTag("description", Model.of("description"), Model.of("PdfBlitz")));
        add(new MetaTag("author", Model.of("author"), Model.of("Ruiu Gabriel Mihai <gabriel.ruiu@mail.com>")));
    }

    protected void generateNavbar() {
        add(new PdfBLitzNavBar("navbar"));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        //AddThis toolbars
        response.render(JavaScriptHeaderItem.forUrl("//s7.addthis.com/js/300/addthis_widget.js#pubid=ra-53954fc92b9605b7"));
        response.render(JavaScriptHeaderItem.forReference(new PackageResourceReference(PdfBlitzPage.class, "addthis.js")));

        //FontAwesome
        response.render(CssHeaderItem.forReference(FontAwesomeCssReference.instance()));

        //GoogleAnalytics
        if (getActiveProfile().equals(ActiveProfile.PROD)) {
            response.render(OnDomReadyHeaderItem.forScript(new PackageTextTemplate(PdfBlitzApplication.class, "properties/prod/google-analytics.js").getString()));
        }

    }

    public ActiveProfile getActiveProfile() {
        return ((PdfBlitzApplication)getApplication()).getActiveProfile();
    }

    private class PdfBLitzNavBar extends Navbar {

        private PdfBLitzNavBar(String componentId) {
            super(componentId);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();
            brandName(Model.of("PdfBlitz"));
            fluid();
            setPosition(Navbar.Position.STATIC_TOP);
            addComponents(getHomeLink());
            addComponents(getContactLink());
        }

        private INavbarComponent getHomeLink() {
            NavbarButton<DashboardPage> homeLink = new NavbarButton<DashboardPage>(DashboardPage.class, Model.of("Home"));
            return new ImmutableNavbarComponent(homeLink);
        }

        private INavbarComponent getContactLink() {
            NavbarButton<ContactPage> contactLink = new NavbarButton<ContactPage>(ContactPage.class, Model.of("Contact"));
            return new ImmutableNavbarComponent(contactLink);
        }
    }

 }
