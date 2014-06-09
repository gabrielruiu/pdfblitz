package com.javastrike.pdfblitz.frontend.page;

import com.timgroup.jgravatar.Gravatar;
import com.timgroup.jgravatar.GravatarDefaultImage;
import com.timgroup.jgravatar.GravatarRating;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ContactPage extends PdfBlitzPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Image image = new Image("gravatar-image", new UrlResourceReference(Url.parse(getGravatarImageUrl())));
        image.add(new AttributeModifier("alt", "Ruiu Gabriel Mihai"));
        add(image);

        createReferences();
    }

    private void createReferences() {

        BootstrapButton websiteLink = new BootstrapButton("reference-link-website", Model.of("www.gabrielruiu.com"), Buttons.Type.Link);
        websiteLink.setIconType(getIcon(FontAwesomeIconTypeBuilder.FontAwesomeGraphic.globe));
        websiteLink.add(new AttributeModifier("target","_blank"));
        websiteLink.add(new AttributeModifier("href", "http://www.gabrielruiu.com"));
        add(websiteLink);

        BootstrapButton mailButton = new BootstrapButton("reference-link-mail", Model.of("gabriel.ruiu@mail.com"), Buttons.Type.Link);
        mailButton.setIconType(getIcon(FontAwesomeIconTypeBuilder.FontAwesomeGraphic.envelope));
        mailButton.add(new AttributeModifier("target", "_blank"));
        mailButton.add(new AttributeModifier("href", "mailto:gabriel.ruiu@mail.com"));
        add(mailButton);

        BootstrapButton githubButton = new BootstrapButton("reference-link-github", Model.of("https://github.com/gabrielruiu/pdfblitz/"), Buttons.Type.Link);
        githubButton.setIconType(getIcon(FontAwesomeIconTypeBuilder.FontAwesomeGraphic.github));
        githubButton.add(new AttributeModifier("target", "_blank"));
        githubButton.add(new AttributeModifier("href", "https://github.com/gabrielruiu/pdfblitz/"));
        add(githubButton);

        BootstrapButton linkedInButton = new BootstrapButton("reference-link-linkedin", Model.of("http://ro.linkedin.com/pub/gabriel-mihai-ruiu/8a/b30/400/"), Buttons.Type.Link);
        linkedInButton.setIconType(getIcon(FontAwesomeIconTypeBuilder.FontAwesomeGraphic.linkedin_square));
        linkedInButton.add(new AttributeModifier("target", "_blank"));
        linkedInButton.add(new AttributeModifier("href", "http://ro.linkedin.com/pub/gabriel-mihai-ruiu/8a/b30/400/ "));
        add(linkedInButton);

        BootstrapButton stackOverflowButton = new BootstrapButton("reference-link-stackoverflow", Model.of("http://stackoverflow.com/users/1542547/gabriel-ruiu"), Buttons.Type.Link);
        stackOverflowButton.setIconType(getIcon(FontAwesomeIconTypeBuilder.FontAwesomeGraphic.stack_overflow));
        stackOverflowButton.add(new AttributeModifier("target", "_blank"));
        stackOverflowButton.add(new AttributeModifier("href", "http://stackoverflow.com/users/1542547/gabriel-ruiu"));
        add(stackOverflowButton);
    }

    private IconType getIcon(FontAwesomeIconTypeBuilder.FontAwesomeGraphic graphic) {
        return FontAwesomeIconTypeBuilder.on(graphic).size(FontAwesomeIconTypeBuilder.Size.two).build();
    }

    private String getGravatarImageUrl() {

        Gravatar gravatar = new Gravatar();
        gravatar.setSize(80);
        gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
        gravatar.setDefaultImage(GravatarDefaultImage.IDENTICON);
        return gravatar.getUrl("gabriel.ruiu@mail.com");
    }
}
