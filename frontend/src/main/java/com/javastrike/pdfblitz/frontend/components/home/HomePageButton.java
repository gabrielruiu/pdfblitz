package com.javastrike.pdfblitz.frontend.components.home;

import com.javastrike.pdfblitz.frontend.theme.PdfBlitzTheme;
import com.vaadin.event.LayoutEvents;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@1and1.ro)
 */
public class HomePageButton extends CustomComponent {


    private VerticalLayout layout;
    private LabelList labelList;

    public HomePageButton(LayoutEvents.LayoutClickListener clickListener,
                          String header, List<String> labels) {

        configureComponent();
        initializeComponents(header, labels);
        configureLayout(clickListener);
        drawContents();
    }

    private void configureComponent() {

        setWidth("360px");
        addStyleName(PdfBlitzTheme.BUTTON_HOMEPAGE);
    }

    private void initializeComponents(String header, List<String> labels) {

        layout = new VerticalLayout();
        labelList = new LabelList(header,labels);
    }

    private void configureLayout(LayoutEvents.LayoutClickListener clickListener) {

        layout.addListener(clickListener);
        layout.setSizeUndefined();
    }

    private void drawContents() {

        layout.addComponent(labelList);
        setCompositionRoot(layout);
    }

    private class LabelList extends VerticalLayout{

        private LabelList(String header, List<String> labels) {

            Label headerComponent = new Label("<div class=\"header\">" +  header.toUpperCase() + "</div>");
            headerComponent.setContentMode(Label.CONTENT_XHTML);
            addComponent(headerComponent);

            Label listStartTag = new Label("<ul>");
            listStartTag.setContentMode(Label.CONTENT_XHTML);
            addComponent(listStartTag);

            for(String label : labels){
                Label labelComponent = new Label(getHTML(label));
                labelComponent.setContentMode(Label.CONTENT_XHTML);
                addComponent(labelComponent);
            }

            Label listEndTag = new Label("</ul>");
            listEndTag.setContentMode(Label.CONTENT_XHTML);
            addComponent(listEndTag);
        }

        private String getHTML(String label){
            return "<li>" + label + "</li>";
        }
    }
}
