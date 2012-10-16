package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener;

import com.javastrike.pdfblitz.frontend.PdfBlitzApplication;
import com.javastrike.pdfblitz.frontend.components.fileupload.UploadType;
import com.javastrike.pdfblitz.frontend.exception.InvalidPageIndices;
import com.javastrike.pdfblitz.frontend.utils.IntegerExpressionProcessor;
import com.javastrike.pdfblitz.manager.exception.DocumentOperationException;
import com.javastrike.pdfblitz.manager.exception.pdfoperations.PdfDocumentOperationException;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
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
    protected List<? extends Document> performOperationOnFiles(List<? extends Document> documents)
        throws DocumentOperationException{

        List<PdfDocument> pdfDocuments;

        try {

            PdfDocument pdfDocument = new PdfDocument(documents.get(0).getContent(),
                    documents.get(0).getName(),documents.get(0).getMimeType());
            pdfDocuments = getPdfDocumentOperations().splitAtPages(pdfDocument, pageIndices);

        } catch (PdfDocumentOperationException e) {
            LOG.error("Error splitting pages",e);
            throw new DocumentOperationException("Error splitting pages", e);
        }
        return pdfDocuments;
    }

    @Override
    protected Layout getOperationInterface() {

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setSizeFull();

        Label informationText = new Label(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getMessage("documentoperation.message.split"));

        TextField inputField = new TextField();
        inputField.setInputPrompt(((PdfBlitzApplication)PdfBlitzApplication.getCurrentApplication()).
                getMessage("documentoperation.message.indexexample"));
        inputField.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

                if (!valueChangeEvent.getProperty().toString().isEmpty()){
                    try {
                        pageIndices = IntegerExpressionProcessor
                                .processIntegerExpression(valueChangeEvent.getProperty().toString());
                    } catch (InvalidPageIndices invalidPageIndices) {
                        PdfBlitzApplication.getCurrentApplication().getMainWindow()
                                .showNotification("The page indices expression is invalid",
                                        Window.Notification.TYPE_ERROR_MESSAGE);
                    }
                }
            }
        });

        layout.addComponent(informationText);
        layout.addComponent(inputField);

        return layout;
    }
}
