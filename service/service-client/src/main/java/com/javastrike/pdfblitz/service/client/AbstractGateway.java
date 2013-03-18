package com.javastrike.pdfblitz.service.client;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import javax.ws.rs.core.MediaType;

import static com.javastrike.pdfblitz.service.client.UrlServiceBulder.url;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@1and1.ro)
 */
public class AbstractGateway {

    private final String baseURL;
    private final MediaType DEFAULT_REQUEST_MEDIA_TYPE = MediaType.APPLICATION_XML_TYPE;


    /**
     * Builds an instance of this class receiving as argument the root URL of the services this gateway will connect to.
     *
     * @param baseURL root URL of the services this gateway will connect to
     */
    public AbstractGateway(final String baseURL) {
        this.baseURL = baseURL;
    }

    /**
     * @return the base url
     */
    public final String getBaseURL() {
        return this.baseURL;
    }

    public <T> T post(String resourceUrl, Object requestBody,  Class<T> returnType) throws Exception{

        String fullUrl = url(this.getBaseURL(), resourceUrl);
        ClientRequest request = new ClientRequest(fullUrl);
        request.accept(DEFAULT_REQUEST_MEDIA_TYPE);

        request.body(DEFAULT_REQUEST_MEDIA_TYPE, requestBody);

        ClientResponse<T> response = request.post(returnType);

        return response.getEntity();
    }


}
