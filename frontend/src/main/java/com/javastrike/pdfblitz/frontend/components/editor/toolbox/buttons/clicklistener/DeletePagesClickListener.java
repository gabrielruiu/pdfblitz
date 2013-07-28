package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener;

import com.javastrike.pdfblitz.frontend.PdfBlitzUI;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DeletePagesClickListener extends DocumentOperationButtonClickListener {


    private static final Logger LOG = Logger.getLogger(DeletePagesClickListener.class);

    private int[] pageIndices;

    public DeletePagesClickListener() {
        super(UploadType.SINGLE, true, "Delete which pages?");
    }

    @Override
    protected List<? extends Document> performOperationOnFiles(List<? extends Document> documents)
        throws DocumentOperationException {

        List<PdfDocument> pdfDocuments;

        try {

            PdfDocument pdfDocument = new PdfDocument(documents.get(0).getContent(), documents.get(0).getName());

            pdfDocuments = new ArrayList<PdfDocument>();
            pdfDocuments.add(getPdfDocumentOperations().deletePages(pdfDocument, pageIndices));

        } catch (PdfDocumentOperationException e) {
            LOG.error("Error deleting pages from pdf document",e);
            throw new DocumentOperationException("Error deleting pages from pdf document", e);
        }
        return pdfDocuments;
    }

    @Override
    protected Layout getOperationInterface() {

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setSizeFull();

        Label informationText = new Label(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getMessage("documentoperation.message.delete"));

        TextField inputField = new TextField();
        inputField.setInputPrompt(((PdfBlitzUI) PdfBlitzUI.getCurrent()).
                getMessage("documentoperation.message.indexexample"));
        inputField.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {

                if (!valueChangeEvent.getProperty().toString().isEmpty()){
                    try {
                        pageIndices = IntegerExpressionProcessor
                                .processIntegerExpression(valueChangeEvent.getProperty().toString());
                    } catch (InvalidPageIndices invalidPageIndices) {
                        Notification.show("The page indices expression is invalid", Notification.Type.ERROR_MESSAGE);
                    }
                }
            }
        });

        layout.addComponent(informationText);
        layout.addComponent(inputField);

        return layout;
    }
}
