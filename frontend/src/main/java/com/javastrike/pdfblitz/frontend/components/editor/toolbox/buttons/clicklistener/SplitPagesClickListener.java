package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener;

import com.javastrike.pdfblitz.frontend.components.fileupload.UploadType;
import com.javastrike.pdfblitz.frontend.utils.IntegerExpressionProcessor;
import com.javastrike.pdfblitz.manager.exception.pdfoperations.PdfDocumentOperationException;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.vaadin.data.Property;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class SplitPagesClickListener extends DocumentOperationButtonClickListener {


    private static final Logger LOG = Logger.getLogger(SplitPagesClickListener.class);

    private int[] pageIndices;

    public SplitPagesClickListener() {
        super(UploadType.SINGLE, true, "Split at which pages?");
    }

    @Override
    protected List<? extends Document> performOperationOnFiles(List<? extends Document> documents) {

        PdfDocument pdfDocument = new PdfDocument(documents.get(0).getContent(),
                documents.get(0).getName(),documents.get(0).getMimeType());

        try {
            return getPdfDocumentOperations().splitAtPages(pdfDocument, pageIndices);

        } catch (PdfDocumentOperationException e) {
            LOG.error("Error splitting pages",e);
        }
        return null;
    }

    @Override
    protected Layout getOperationInterface() {

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setSizeFull();

        Label informationText = new Label("Choose at which pages you would like to separate your document");

        TextField inputField = new TextField();
        inputField.setInputPrompt("Example: 1,2,8-10");
        inputField.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

                if (!valueChangeEvent.getProperty().toString().isEmpty()){
                    pageIndices = IntegerExpressionProcessor
                            .processIntegerExpression(valueChangeEvent.getProperty().toString());
                }
            }
        });

        layout.addComponent(informationText);
        layout.addComponent(inputField);

        return layout;
    }
}
