package com.javastrike.pdfblitz.frontend.zip;

import com.javastrike.pdfblitz.frontend.zip.exception.ArchivingException;
import com.javastrike.pdfblitz.manager.model.Document;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class DefaultDocumentArchiver/* implements ZipArchiver<Document>*/ {


    private static final Logger LOG = Logger.getLogger(DefaultDocumentArchiver.class);


    public void archive(List<? extends Document> archiveEntries, OutputStream outputStream)
            throws ArchivingException {

        try {
            ArchiveOutputStream archiveStream = new ArchiveStreamFactory().
                    createArchiveOutputStream(ZipArchiver.ARCHIVE_EXTENSION, outputStream);

            for (Document document : archiveEntries) {

                archiveStream.putArchiveEntry(new ZipArchiveEntry(document.getName()));

                ByteArrayInputStream byteInputStream = new ByteArrayInputStream(document.getContent());
                IOUtils.copy(byteInputStream, archiveStream);
                IOUtils.closeQuietly(byteInputStream);

                archiveStream.closeArchiveEntry();
            }
            archiveStream.finish();
            IOUtils.closeQuietly(archiveStream);

        } catch (ArchiveException e) {
            LOG.error("Error compressing into zip archive", e);
            throw new ArchivingException("Error compressing into zip archive",e);
        } catch (IOException e) {
            LOG.error("Error compressing into zip archive", e);
            throw new ArchivingException("Error compressing into zip archive",e);
        }
    }
}
