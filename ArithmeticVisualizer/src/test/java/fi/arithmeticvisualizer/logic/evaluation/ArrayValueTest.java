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
    static ArrayValue scalarThree;

    public ArrayValueTest() {
    }

    @BeforeClass
    public static void setUpClass() throws BadArrayException {

        av1 = new ArrayValue("1 2 3; 4 5 6");
        av2 = new ArrayValue("1 2; 3 4; 5 6");
        av3 = new ArrayValue("1 4; 2 5; 3 6");
        av4 = new ArrayValue("2 4 6; 8 10 12");
        av5 = new ArrayValue("1.0 2.0 3.0; 4.0 5.0 6.0");
        scalarThree = new ArrayValue("3");
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
    
    @Test(expected = BadArrayException.class)
    public void noRowsCausesException() throws BadArrayException {
        new ArrayValue("");
    }
    
    @Test(expected = BadArrayException.class)
    public void emptyRowCausesException() throws BadArrayException {
        new ArrayValue("1;; 2");
    }
    
    @Test(expected = BadArrayException.class)
    public void badStringCausesException() throws BadArrayException {
        new ArrayValue("lolcats");
    }

    @Test
    public void constructionFromArrayWorks() {
        double[][] array = new double[][]{{1, 2, 3}, {4, 5, 6}};
        ArrayValue av = new ArrayValue(array);
        assertArrayEquals(array[0], av.getValue()[0], .001);
        assertArrayEquals(array[1], av.getValue()[1], .001);
    }

    @Test
    public void constructionFromStringWorks() throws BadArrayException {
        double[][] array = new double[][]{{1, 2, 3}, {4, 5, 6}};
        String string = "1 2 3; 4 5 6";
        ArrayValue av = new ArrayValue(string);
        assertArrayEquals(array[0], av.getValue()[0], .001);
        assertArrayEquals(array[1], av.getValue()[1], .001);
    }

    @Test
    public void dotProductWorks() {
        double[] row = new double[]{1, 2, 3, 4, 5};
        double[] column = new double[]{1, 2, 3, 4, 5};

        assertEquals(55, ArrayValue.dotVectors(row, column), .0001);
    }
    
    @Test
    public void matrixMultiplyWorks() {
        ArrayValue result = av1.multiply(av2);
        assertEquals(new Dimensions(2, 2), result.getDimensions());
        assertEquals(22, result.getElement(0, 0), .0001);
        assertEquals(28, result.getElement(0, 1), .0001);
        assertEquals(49, result.getElement(1, 0), .0001);
        assertEquals(64, result.getElement(1, 1), .0001);
    }

    @Test
    public void getRowWorks() {
        double[] row0 = new double[]{1, 2, 3};
        double[] row1 = new double[]{4, 5, 6};
        assertArrayEquals(row0, av1.getRow(0), .001);
        assertArrayEquals(row1, av1.getRow(1), .001);
    }
    
    @Test
    public void getColumnWorks() {
        double[] col0 = new double[]{1, 3, 5};
        double[] col1 = new double[]{2, 4, 6};
        assertArrayEquals(col0, av2.getColumn(0), .001);
        assertArrayEquals(col1, av2.getColumn(1), .001);
    }

    @Test
    public void transposeWorks() {
        assertArrayEquals(av3.getValue(), av1.transpose().getValue());
        assertEquals(3.0, scalarThree.transpose().getElement(0, 0), .001);
    }

    @Test
    public void scalarMultiplicationWorks() {
        assertArrayEquals(av4.getValue()[0], av1.scalarMultiply(2).getValue()[0], .001);
    }

    @Test
    public void multiplicationByScalarWorks() throws BadArrayException {
        ArrayValue scalarTwo = new ArrayValue("2");
        assertArrayEquals(av4.getValue()[0], av1.multiply(scalarTwo).getValue()[0], .001);
        assertArrayEquals(av4.getValue()[0], scalarTwo.multiply(av1).getValue()[0], .001);
    }

    @Test
    public void toInputStringWorks() {
        assertEquals("1.0 2.0 3.0; 4.0 5.0 6.0", av1.toInputString());
    }
    
                
    
    
}
