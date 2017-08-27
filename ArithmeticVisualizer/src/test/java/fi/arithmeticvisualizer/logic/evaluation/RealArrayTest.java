/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.arithmeticvisualizer.logic.evaluation;

import static fi.arithmeticvisualizer.logic.evaluation.RealArray.dotVectors;
import static fi.arithmeticvisualizer.logic.evaluation.RealArray.stringToArray;
import static fi.arithmeticvisualizer.logic.evaluation.RealArray.stringToRow;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juha
 */
public class RealArrayTest {

    public RealArrayTest() {
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
    public void dotProductWorks() {
        double[] row = new double[]{1, 2, 3, 4, 5};
        double[] column = new double[]{1, 2, 3, 4, 5};

        assertEquals(55, dotVectors(row, column), .0001);
    }
    
    @Test(expected = BadArrayException.class)
    public void stringToRowThrowsExceptionOnNonsenseString() throws BadArrayException {
        stringToRow("lolcat");
    }
    
    @Test(expected = BadArrayException.class)
    public void stringToRowThrowsExceptionOnComma() throws BadArrayException {
        stringToRow("1,0 3 4");
    }
    
    @Test(expected = BadArrayException.class)
    public void stringToArrayThrowsExceptionOnEmptyString() throws BadArrayException {
        stringToArray("");
    }
    
    @Test(expected = BadArrayException.class)
    public void stringToArrayThrowsExceptionOnJaggedArray() throws BadArrayException {
        stringToArray("1 2 3; 4 5; 6 7 8");
    }
}
