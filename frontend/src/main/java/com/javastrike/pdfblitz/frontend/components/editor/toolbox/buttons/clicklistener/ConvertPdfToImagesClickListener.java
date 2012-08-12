package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener;

import com.javastrike.pdfblitz.frontend.components.fileupload.UploadType;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.model.ImageDocument;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.vaadin.ui.Layout;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ConvertPdfToImagesClickListener extends DocumentOperationButtonClickListener {

    private static final Logger LOG = Logger.getLogger(ConvertPdfToImagesClickListener.class);

    public ConvertPdfToImagesClickListener() {
        super(UploadType.SINGLE,false, "");
    }

    @Override
    protected List<? extends Document> performOperationOnFiles(List<? extends Document> documents) {

        try {
            List<ImageDocument> imageDocuments = new ArrayList<ImageDocument>();
            imageDocuments.addAll(getConversionOperations().convertDocumentToImages(
                    convertDocumentToPdf(documents.get(0))));

            return imageDocuments;

        } catch (ConversionException e) {
            LOG.error("Error converting images into pdf documents",e);
        }
        return null;
    }

    @Override
    protected Layout getOperationInterface() {
        return null;
    }


    private PdfDocument convertDocumentToPdf(Document document) {
            return new PdfDocument(document.getContent(),document.getName(),document.getMimeType());
    }
}
