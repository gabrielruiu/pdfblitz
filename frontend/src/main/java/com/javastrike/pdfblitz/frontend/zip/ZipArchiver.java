package com.javastrike.pdfblitz.frontend.zip;

import com.javastrike.pdfblitz.frontend.zip.exception.CompressionException;

import java.io.OutputStream;
import java.util.List;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public interface ZipArchiver<T> {

    String ARCHIVE_NAME = "zip";

    void archive(List<T> archiveEntries, OutputStream outputStream) throws CompressionException;
}
