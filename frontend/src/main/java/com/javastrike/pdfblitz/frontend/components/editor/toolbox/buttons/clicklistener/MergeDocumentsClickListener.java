package com.javastrike.pdfblitz.frontend.components.editor.toolbox.buttons.clicklistener;

import com.javastrike.pdfblitz.frontend.components.fileupload.UploadType;
import com.javastrike.pdfblitz.manager.converter.impl.DefaultConversionContext;
import com.javastrike.pdfblitz.manager.converter.impl.StringConversionParameter;
import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.converter.management.IdentifierType;
import com.javastrike.pdfblitz.manager.exception.DocumentOperationException;
import com.javastrike.pdfblitz.manager.exception.pdfoperations.PdfDocumentOperationException;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.model.PdfDocument;
import com.vaadin.ui.Layout;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class MergeDocumentsClickListener extends DocumentOperationButtonClickListener {

    private static final Logger LOG = Logger.getLogger(MergeDocumentsClickListener.class);

    public MergeDocumentsClickListener() {
        super(UploadType.MULTIPLE, false, "");
    }

    @Override
    protected List<? extends Document> performOperationOnFiles(List<? extends Document> documents)
        throws DocumentOperationException {

        List<PdfDocument> pdfDocuments;
        try {
            ConversionContext context = new DefaultConversionContext().
                    addConversionParameter(IdentifierType.DOCUMENT_NAME, new StringConversionParameter("merged_documents.pdf")).
                    addConversionParameter(IdentifierType.MIME_TYPE, new StringConversionParameter("application/pdf"));

            pdfDocuments = new ArrayList<PdfDocument>();
            pdfDocuments.add(getPdfDocumentOperations().mergeDocuments(context, convertDocumentsToPdfs(documents)));

        } catch (PdfDocumentOperationException e) {
            LOG.error("Error merging documents",e);
            throw new DocumentOperationException("Error merging documents", e);
        }
        return pdfDocuments;
    }

    @Override
    protected Layout getOperationInterface() {
        return null;
    }




    private PdfDocument[] convertDocumentsToPdfs(List<? extends Document> documents) {

        PdfDocument[] pdfDocuments = new PdfDocument[documents.size()];
        for (int i = 0; i<documents.size(); i++) {

            Document document = documents.get(i);
            pdfDocuments[i] = new PdfDocument(document.getContent(),document.getName());
        }
        return pdfDocuments;
    }
}
