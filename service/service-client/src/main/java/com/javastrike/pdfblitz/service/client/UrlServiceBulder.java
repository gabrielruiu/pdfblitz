package com.javastrike.pdfblitz.service.client;

import java.util.Map;

/**
 * @author Daniel Grigore
 */
public final class UrlServiceBulder {


    private UrlServiceBulder() {

    }

    public static String url(String baseUrl, String path, Map<String, ?> queryParams) {

        StringBuilder url = new StringBuilder(baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1)
                : baseUrl);
        if (!path.startsWith("/")) {
            url.append("/");
        }
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        url.append(path);
        if (queryParams != null) {
            url.append(queryString(queryParams));
        }
        return url.toString();
    }

    public static String url(final String baseUrl, final String path) {
        return url(baseUrl, path, null);
    }

    private static String queryString(final Map<String, ?> params) {

        StringBuilder qs = new StringBuilder();
        for (Map.Entry<String, ?> entry : params.entrySet()) {
            Object value = entry.getValue();
            if (value.getClass().isArray()) {
                for (Object v : (Object[]) value) {
                    addQueryParam(qs, entry.getKey(), v.toString());
                }
            } else {
                addQueryParam(qs, entry.getKey(), value.toString());
            }
        }
        return qs.toString();
    }

    private static void addQueryParam(final StringBuilder sb, final String name, final String value) {

        sb.append(sb.length() == 0 ? '?' : '&');
        sb.append(name).append('=').append(value);
    }
}