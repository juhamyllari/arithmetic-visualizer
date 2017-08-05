package av.arithmeticvisualizer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UtilsTest {

    static double[][] scalarAdditionResult;
    static double[][] matrixAdditionResult;
    static double[][] matrixMultiplicationResult;
    static double[][] matrixA;
    static double[][] matrixB;
    static double[][] matrixC;
    static double[][] arrayThree;
    static double[][] arrayFour;

    public UtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        matrixA = new double[][]{{1.1, 2.2, 3.3}, {1.0, 2.0, 3.0}};
        matrixB = new double[][]{{1.0, 1.0, 1.0}, {1.0, 1.0, 1.0}};
        matrixC = new double[][]{{2.0, 3.0}, {4.0, 5.0}, {6.0, 7.0}};
        arrayThree = new double[][]{{3.0}};
        arrayFour = new double[][]{{4.0}};
        
       

        scalarAdditionResult = Utils.addArrays(
                arrayThree, arrayFour);

        matrixAdditionResult = Utils.addArrays(matrixA, matrixB);

        matrixMultiplicationResult = Utils.matrixMultiply(matrixA, matrixC);
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
    public void scalarPlusScalarGivesScalar() {
        assertEquals(1, scalarAdditionResult.length);
        assertEquals(1, scalarAdditionResult[0].length);
    }

    @Test
    public void scalarsAddedCorrectly() {
        assertEquals(7.0, scalarAdditionResult[0][0], 0.001);
    }

    @Test
    public void matricesAddedCorrectly() {
        assertEquals(4.3, matrixAdditionResult[0][2], 0.001);
    }

    @Test
    public void matrixMultiplicationGivesCorrectShape() {
        assertEquals(2, matrixMultiplicationResult.length);
        assertEquals(2, matrixMultiplicationResult[0].length);
    }
    
    @Test
    public void matrixMultiplicationGivesCorrectResult() {
        assertEquals(37.4, matrixMultiplicationResult[0][1], .001);
    }
    
    @Test
    public void scalarMultiplicationFromLeftWorks() {
        assertEquals(21.0, Utils.multiplyArrays(arrayThree, matrixC)[2][1], .001);
    }
    
    @Test
    public void scalarMultiplicationFromRightWorks() {
        assertEquals(21.0, Utils.multiplyArrays(matrixC, arrayThree)[2][1], .001);
    }
    
    
    

}
