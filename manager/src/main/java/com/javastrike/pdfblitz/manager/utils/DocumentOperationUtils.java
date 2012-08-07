package com.javastrike.pdfblitz.manager.utils;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.util.Arrays;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public abstract class DocumentOperationUtils {


    private DocumentOperationUtils() {
        throw new AssertionError("Cannot instantiate " + DocumentOperationUtils.class.getName());
    }


    public static boolean indicesAreAllInRange(int totalNumberOfPages, int... pageIndices) {

        for (int pageIndex : pageIndices) {
            if (pageIndex > totalNumberOfPages || pageIndex < 1) {
                return false;
            }
        }
        return true;
    }

    public static void orderIndicesAscending(int... pageIndices) {
        Arrays.sort(pageIndices);
    }

    public static boolean prepareAndCheckPageIndices(int totalNumberOfPages, int... pageIndices) {

        //check if any of the indices is out of range
        if (!DocumentOperationUtils.indicesAreAllInRange(totalNumberOfPages, pageIndices)) {
            return false;
        }

        //order the pageIndices to be in ascending order
        DocumentOperationUtils.orderIndicesAscending(pageIndices);
        return true;
    }
}
