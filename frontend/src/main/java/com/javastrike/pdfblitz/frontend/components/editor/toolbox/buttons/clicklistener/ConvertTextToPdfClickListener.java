package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener;

import com.javastrike.pdfblitz.frontend.components.fileupload.UploadType;
import com.javastrike.pdfblitz.manager.exception.DocumentOperationException;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.javastrike.pdfblitz.manager.model.TextDocument;
import com.vaadin.ui.Layout;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ConvertTextToPdfClickListener extends DocumentOperationButtonClickListener {


    private static final Logger LOG = Logger.getLogger(ConvertTextToPdfClickListener.class);


    public ConvertTextToPdfClickListener() {
        super(UploadType.SINGLE, false, "");
    }

    @Override
    protected List<? extends Document> performOperationOnFiles(List<? extends Document> documents)
        throws DocumentOperationException {

        List<PdfDocument> pdfDocuments;

        try {

            Document document = documents.get(0);
            TextDocument textDocument = new TextDocument();
            textDocument.setContent(document.getContent());
            textDocument.setName(document.getName());
            textDocument.setMimeType(document.getMimeType());

            pdfDocuments = new ArrayList<PdfDocument>();
            pdfDocuments.add(getConversionOperations().convertTextToPdfDocument(textDocument, null));

        } catch (ConversionException e) {
            LOG.error("Error converting text to pdf", e);
            throw new DocumentOperationException("Error converting text to pdf", e);
        }

        return pdfDocuments;
    }

    @Override
    protected Layout getOperationInterface() {
        return null;
    }
}
