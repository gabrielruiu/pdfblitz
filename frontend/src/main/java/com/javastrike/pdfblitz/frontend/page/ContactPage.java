package com.javastrike.pdfblitz.frontend.page;

import com.timgroup.jgravatar.Gravatar;
import com.timgroup.jgravatar.GravatarDefaultImage;
import com.timgroup.jgravatar.GravatarRating;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.image.Image;
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
        image.add(new AttributeModifier("alt","Ruiu Gabriel Mihai"));

        add(image);
    }

    private String getGravatarImageUrl() {
        Gravatar gravatar = new Gravatar();
        gravatar.setSize(300);
        gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
        gravatar.setDefaultImage(GravatarDefaultImage.IDENTICON);

        return gravatar.getUrl("gabriel.ruiu@mail.com");
    }
}
