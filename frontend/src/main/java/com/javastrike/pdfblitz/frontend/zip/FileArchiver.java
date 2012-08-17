package com.javastrike.pdfblitz.frontend.zip;

import com.javastrike.pdfblitz.frontend.zip.exception.ArchivingException;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class FileArchiver implements ZipArchiver<File> {


    private static final Logger LOG = Logger.getLogger(FileArchiver.class);


    @Override
    public void archive(List<File> archiveEntries, OutputStream outputStream) throws ArchivingException {

        try {
            ArchiveOutputStream archiveStream = new ArchiveStreamFactory().
                    createArchiveOutputStream(ZipArchiver.ARCHIVE_EXTENSION, outputStream);

            for (File file : archiveEntries) {

                archiveStream.putArchiveEntry( new ZipArchiveEntry(file, file.getName()));

                FileInputStream fileInputStream = new FileInputStream(file);
                IOUtils.copy(fileInputStream, archiveStream);
                IOUtils.closeQuietly(fileInputStream);

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
