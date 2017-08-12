package fi.arithmeticvisualizer.logic.utils;

import static fi.arithmeticvisualizer.logic.utils.ArrayIOUtils.stringToArray;
import static fi.arithmeticvisualizer.logic.utils.ArrayIOUtils.stringToRow;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArrayIOUtilsTest {

    public ArrayIOUtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void stringToRowWorks() throws BadArrayException {

        String str1 = "1 2.0 3 4 5 6";
        double[] row1 = stringToRow(str1);

        String str2 = "1 2      3  4 5.0 6";
        double[] row2 = stringToRow(str2);

        for (int i = 0; i < row1.length; i++) {
            Assert.assertEquals(i + 1, row1[i], 0.0001);
            Assert.assertEquals(i + 1, row2[i], 0.0001);
        }
    }
    
    @Test(expected = BadArrayException.class)
    public void stringToRowThrowsBadArrayExceptionOnEmptyString() throws BadArrayException {
        stringToRow("");
    }
    
    @Test(expected = BadArrayException.class)
    public void stringToRowThrowsBadArrayExceptionOnNonNumericString() throws BadArrayException {
        stringToRow("lolcat");
    }
    
    @Test(expected = BadArrayException.class)
    public void stringToRowThrowsBadArrayExceptionOnBadlyFormattedString1() throws BadArrayException {
        stringToRow("1 2 3;");
    }
    
    @Test(expected = BadArrayException.class)
    public void stringToRowThrowsBadArrayExceptionOnBadlyFormattedString2() throws BadArrayException {
        stringToRow("1, 2 3");
    }
    
    @Test
    public void stringToArrayReturnsCorrectArray() throws BadArrayException {
        String str1 = "0 1. 2; 1 2   3.; 2.0   3.0 4";
        double[][] array1 = stringToArray(str1);
        
        System.out.println(Arrays.deepToString(array1));
        
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1.length; j++) {
                Assert.assertEquals(i + j, array1[i][j], 0.0001);
            }
        }
        
    }
    
    @Test(expected = BadArrayException.class)
    public void stringToArrayThrowsBadArrayExceptionOnJaggedArray() throws BadArrayException {
        stringToArray("1 2 3; 4 4; 5 6 7");
    }

    
}
