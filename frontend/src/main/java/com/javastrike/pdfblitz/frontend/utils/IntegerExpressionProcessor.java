package com.javastrike.pdfblitz.frontend.utils;

import com.javastrike.pdfblitz.frontend.exception.InvalidPageIndices;
import org.apache.commons.collections.primitives.ArrayIntList;

import java.util.regex.PatternSyntaxException;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public abstract class IntegerExpressionProcessor {


    private IntegerExpressionProcessor() {
        throw new AssertionError("Cannot instantiate " + getClass().getName());
    }

    //splitting the string from the "-" character means that negative numbers will not
    //be processed correctly and will return a runtime exception
    public static int[] processIntegerExpression(String integerExpression) throws InvalidPageIndices{

        ArrayIntList integers = new ArrayIntList();

        try {
            String[] parts = integerExpression.split(",");

            for (String part : parts) {

                if (part.contains("-")) {
                    String[] startAndEndIndices = part.split("-");
                    int startIndex = Integer.parseInt(startAndEndIndices[0]);
                    int endIndex = Integer.parseInt(startAndEndIndices[1]);

                    for (int index : generateFullIntegerRange(startIndex,endIndex)) {
                        integers.add(index);
                    }
                } else {
                    integers.add(Integer.parseInt(part));
                }
            }

        } catch (PatternSyntaxException e) {
            throw new InvalidPageIndices("Page indices expression is invalid", e);
        } catch (NumberFormatException e) {
            throw new InvalidPageIndices("Page indices expression is invalid", e);
        }

        return integers.toArray();
    }

    public static int[] generateFullIntegerRange(int startIndex, int endIndex) {

        ArrayIntList integerRange = new ArrayIntList();

        for (int index = startIndex; index<=endIndex; index ++) {
            integerRange.add(index);
        }
        return integerRange.toArray();
    }


}
