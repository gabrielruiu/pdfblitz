package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener;

import com.javastrike.pdfblitz.frontend.components.fileupload.UploadType;
import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.converter.management.IdentifierType;
import com.javastrike.pdfblitz.manager.converter.management.impl.StringConversionParameter;
import com.javastrike.pdfblitz.manager.exception.DocumentOperationException;
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
public class ConvertImagesToPdfClickListener extends DocumentOperationButtonClickListener {


    private static final Logger LOG = Logger.getLogger(ConvertPdfToImagesClickListener.class);

    public ConvertImagesToPdfClickListener() {
        super(UploadType.MULTIPLE,false, "");
    }

    @Override
    protected List<? extends Document> performOperationOnFiles(List<? extends Document> documents)
        throws DocumentOperationException{

        List<PdfDocument> pdfDocuments;
        try {

            ConversionContext context = new ConversionContext().
                    addConversionParameter(IdentifierType.DOCUMENT_NAME,
                            new StringConversionParameter("merged_images.pdf")).
                    addConversionParameter(IdentifierType.MIME_TYPE, new StringConversionParameter("application/pdf"));

            List<ImageDocument> imageDocuments = convertDocumentsToImages(documents);

            pdfDocuments = new ArrayList<PdfDocument>();
            pdfDocuments.add(getConversionOperations().convertImagesToPdfDocument(imageDocuments,context));

        } catch (ConversionException e) {
            LOG.error("Error converting images into pdf documents",e);
            throw new DocumentOperationException("Error converting images into pdf documents", e);
        }
        return pdfDocuments;
    }

    @Override
    protected Layout getOperationInterface() {
        return null;
    }


    private List<ImageDocument> convertDocumentsToImages(List<? extends Document> documents) {

        List<ImageDocument> images = new ArrayList<ImageDocument>();
        for (Document document : documents) {
            images.add(new ImageDocument(document.getContent(),document.getName()));
        }
        return images;
    }
}
