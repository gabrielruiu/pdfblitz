package com.javastrike.pdfblitz.manager.utils;

import org.apache.commons.collections.primitives.ArrayIntList;

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

    public static int[] removeDuplicateIntegerEntries(int[] integers) {

        ArrayIntList noDuplicateIntegers = new ArrayIntList();

        for (int value : integers) {
            if (! noDuplicateIntegers.contains(value)) {
                noDuplicateIntegers.add(value);
            }
        }

        return noDuplicateIntegers.toArray();
    }

    public static boolean prepareAndCheckPageIndices(int totalNumberOfPages, int... pageIndices) {

        //check if any of the indices is out of range
        if (!DocumentOperationUtils.indicesAreAllInRange(totalNumberOfPages, pageIndices)) {
            return false;
        }

        //order the pageIndices to be in ascending order
        DocumentOperationUtils.orderIndicesAscending(pageIndices);


        //remove any integer values that appear more than once
        pageIndices = removeDuplicateIntegerEntries(pageIndices);

        return true;
    }
}
