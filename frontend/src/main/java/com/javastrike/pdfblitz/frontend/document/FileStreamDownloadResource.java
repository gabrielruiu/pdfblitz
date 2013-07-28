package com.javastrike.pdfblitz.frontend.document;


import com.vaadin.server.DownloadStream;
import com.vaadin.server.StreamResource;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@1and1.ro)
 */
public class FileStreamDownloadResource extends StreamResource {


    private static final Logger LOG = Logger.getLogger(FileStreamDownloadResource.class);


    public FileStreamDownloadResource(StreamSource streamSource, String filename) {
        super(streamSource, filename);
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
