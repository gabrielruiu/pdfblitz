package com.javastrike.pdfblitz.test.utils;

import com.javastrike.pdfblitz.frontend.utils.IntegerExpressionProcessor;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@1and1.ro)
 */
@Test
public class IntegerExpressionProcessorTest {


    @Test
    public void integerExpressionProcessorTest1() {

        String integerExpression = "1,4,5,7,8-10";
        int[] expectedArray = {1, 4, 5, 7 ,8 ,9 , 10};

        int[] actualValue = IntegerExpressionProcessor.processIntegerExpression(integerExpression);
        assertEquals(actualValue, expectedArray);
    }
}
