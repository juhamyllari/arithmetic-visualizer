package fi.arithmeticvisualizer.logic.evaluation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayValueTest {

    static ArrayValue av1;
    static ArrayValue av2;
    static ArrayValue av3;
    static ArrayValue av4;
    static ArrayValue av5;

    public ArrayValueTest() {
    }

    @BeforeClass
    public static void setUpClass() throws BadArrayException {

        av1 = new ArrayValue("1 2 3; 4 5 6");
        av2 = new ArrayValue("1 2; 3 4; 5 6");
        av3 = new ArrayValue("1 4; 2 5; 3 6");
        av4 = new ArrayValue("2 4 6; 8 10 12");
        av5 = new ArrayValue("1.0 2.0 3.0; 4.0 5.0 6.0");
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

    @Test(expected = BadArrayException.class)
    public void constructorFromStringThrowsCorrectException() throws BadArrayException {
        new ArrayValue("1 2; 3 4 5");
    }

// Test fails! Awaiting debugging.    
//    @Test
//    public void matrixMultiplicationWorks() {
//        ArrayValue av = av1.multiply(av2);
//    }
    @Test
    public void dotProductWorks() {
        double[] row = new double[]{1, 2, 3, 4, 5};
        double[] column = new double[]{1, 2, 3, 4, 5};

        assertEquals(55, ArrayValue.dotVectors(row, column), .0001);
    }

    @Test
    public void getRowWorks() {
        double[] row0 = new double[]{1, 2, 3};
        double[] row1 = new double[]{4, 5, 6};
        assertArrayEquals(row0, av1.getRow(0), .001);
        assertArrayEquals(row1, av1.getRow(1), .001);
    }

    @Test
    public void transposeWorks() {
        assertArrayEquals(av3.getValue(), av1.transpose().getValue());
    }

    @Test
    public void scalarMultiplicationWorks() {
        assertArrayEquals(av4.getValue()[0], av1.scalarMultiply(2).getValue()[0], .001);
    }

    @Test
    public void multiplicationByScalarWorks() throws BadArrayException {
        ArrayValue scalarTwo = new ArrayValue("2");
        assertArrayEquals(av4.getValue()[0], av1.scalarMultiply(2).getValue()[0], .001);
    }
    
    @Test
    public void toInputStringWorks() {
        assertEquals("1.0 2.0 3.0; 4.0 5.0 6.0", av1.toInputString());
    }
}
