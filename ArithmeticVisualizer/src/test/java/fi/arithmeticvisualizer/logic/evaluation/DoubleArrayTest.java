package fi.arithmeticvisualizer.logic.evaluation;

import static fi.arithmeticvisualizer.logic.evaluation.RealArray.dotVectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoubleArrayTest {

    static DoubleArray av1;
    static DoubleArray av2;
    static DoubleArray av3;
    static DoubleArray av4;
    static DoubleArray av5;
    static DoubleArray scalarThree;

    public DoubleArrayTest() {
    }

    @BeforeClass
    public static void setUpClass() throws BadArrayException {

        av1 = new DoubleArray("1 2 3; 4 5 6");
        av2 = new DoubleArray("1 2; 3 4; 5 6");
        av3 = new DoubleArray("1 4; 2 5; 3 6");
        av4 = new DoubleArray("2 4 6; 8 10 12");
        av5 = new DoubleArray("1.0 2.0 3.0; 4.0 5.0 6.0");
        scalarThree = new DoubleArray("3");
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
        new DoubleArray("1 2; 3 4 5");
    }
    
    @Test(expected = BadArrayException.class)
    public void noRowsCausesException() throws BadArrayException {
        new DoubleArray("");
    }
    
    @Test(expected = BadArrayException.class)
    public void emptyRowCausesException() throws BadArrayException {
        new DoubleArray("1;; 2");
    }
    
    @Test(expected = BadArrayException.class)
    public void badStringCausesException() throws BadArrayException {
        new DoubleArray("lolcats");
    }

    @Test
    public void constructionFromArrayWorks() {
        double[][] array = new double[][]{{1, 2, 3}, {4, 5, 6}};
        DoubleArray av = new DoubleArray(array);
        assertArrayEquals(array[0], av.getRow(0), .001);
        assertArrayEquals(array[1], av.getRow(1), .001);
    }

    @Test
    public void constructionFromStringWorks() throws BadArrayException {
        double[][] array = new double[][]{{1, 2, 3}, {4, 5, 6}};
        String string = "1 2 3; 4 5 6";
        DoubleArray av = new DoubleArray(string);
        assertArrayEquals(array[0], av.getRow(0), .001);
        assertArrayEquals(array[1], av.getRow(1), .001);
    }

    @Test
    public void dotProductWorks() {
        double[] row = new double[]{1, 2, 3, 4, 5};
        double[] column = new double[]{1, 2, 3, 4, 5};

        assertEquals(55, dotVectors(row, column), .0001);
    }
    
    @Test
    public void matrixMultiplyWorks() {
        RealArray result = av1.multiply(av2);
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
        assertEquals(3.0, scalarThree.transpose().getElement(0, 0), .001);
    }

    @Test
    public void scalarMultiplicationWorks() {
        assertArrayEquals(av4.getRow(0), av1.scalarMultiply(2).getRow(0), .001);
    }

    @Test
    public void multiplicationByScalarWorks() throws BadArrayException {
        DoubleArray scalarTwo = new DoubleArray("2");
        assertArrayEquals(av4.getRow(0), av1.multiply(scalarTwo).getRow(0), .001);
        assertArrayEquals(av4.getRow(0), scalarTwo.multiply(av1).getRow(0), .001);
    }

    @Test
    public void toInputStringWorks() {
        assertEquals("1.0 2.0 3.0; 4.0 5.0 6.0", av1.toInputString());
    }
    
                
    
    
}
