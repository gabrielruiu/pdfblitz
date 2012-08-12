package com.javastrike.pdfblitz.frontend.utils;

import java.util.Map;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */

//TODO: load using spring
public abstract class ApplicationPropertiesUtils {


    private static Map<String, String> applicationProperties;

    private ApplicationPropertiesUtils() {
        throw new AssertionError("Cannot instantiate " + getClass().getName());
    }

    public static String getProperty(String name) {

	        return applicationProperties.get(name);
    }

    private static void initApplicationProperties() {


    }

}
