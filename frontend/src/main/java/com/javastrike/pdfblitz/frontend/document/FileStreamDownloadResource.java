package com.javastrike.pdfblitz.frontend.document;

import com.vaadin.Application;
import com.vaadin.terminal.DownloadStream;
import com.vaadin.terminal.StreamResource;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@1and1.ro)
 */
public class FileStreamDownloadResource extends StreamResource {


    private static final Logger LOG = Logger.getLogger(FileStreamDownloadResource.class);


    public FileStreamDownloadResource(StreamSource streamSource, String filename, Application application) {
        super(streamSource, filename, application);
    }


    @Override
    public DownloadStream getStream() {

        DownloadStream downloadStream = new DownloadStream(getStreamSource().getStream(),getMIMEType(), getFilename());
        downloadStream.setCacheTime(getCacheTime());
        try {
            downloadStream.setParameter("Content-Disposition", "attachment; filename=" +
                    URLEncoder.encode(getFilename(), "utf-8"));
        } catch (UnsupportedEncodingException e){
            LOG.error("Error creating FileStreamDownloadResource", e);
            return null;
        }
        return downloadStream;

    }
}
