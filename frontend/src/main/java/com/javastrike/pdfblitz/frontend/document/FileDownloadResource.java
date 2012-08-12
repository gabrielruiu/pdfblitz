package com.javastrike.pdfblitz.frontend.document;

import com.vaadin.Application;
import com.vaadin.terminal.DownloadStream;
import com.vaadin.terminal.FileResource;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class FileDownloadResource extends FileResource {


    private static final Logger LOG = Logger.getLogger(FileDownloadResource.class);

    public FileDownloadResource(File sourceFile, Application application) {
        super(sourceFile, application);
    }

    @Override
    public DownloadStream getStream() {

        try {
            final DownloadStream ds = new DownloadStream(new FileInputStream(
                    getSourceFile()), getMIMEType(), getFilename());
            ds.setParameter("Content-Disposition", "attachment; filename="+
                    URLEncoder.encode(getFilename(), "utf-8"));
            ds.setCacheTime(getCacheTime());
            return ds;
        } catch (final FileNotFoundException e) {
            LOG.error("Error creating FileDownloadResource",e);
            return null;
        } catch (UnsupportedEncodingException e){
            LOG.error("Error creating FileDownloadResource",e);
            return null;
        }
    }
}


