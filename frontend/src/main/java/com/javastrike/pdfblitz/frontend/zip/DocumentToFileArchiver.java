package com.javastrike.pdfblitz.frontend.zip;

import com.javastrike.pdfblitz.frontend.zip.exception.CompressionException;
import com.javastrike.pdfblitz.manager.converter.impl.document.DocumentFileConverter;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DocumentToFileArchiver /*implements ZipArchiver<Document>*/ {


    private static final Logger LOG = Logger.getLogger(DocumentToFileArchiver.class);


    public void archive(List<? extends Document> archiveEntries, OutputStream outputStream) throws CompressionException {

        try {

            List<File> files = convertEachDocumentIntoFile(archiveEntries);
            new FileArchiver().archive(files, outputStream);

        } catch (ConversionException e) {
            LOG.error("Error compressing into zip archive", e);
            throw new CompressionException("Error compressing into zip archive",e);
        }
    }

    private List<File> convertEachDocumentIntoFile(List<? extends Document> documents) throws ConversionException {

        List<File> files = new ArrayList<File>();
        DocumentFileConverter converter = new DocumentFileConverter();

        for (Document document : documents) {
            files.add(converter.provideDocument(document, null));
        }
        return files;
    }
}
