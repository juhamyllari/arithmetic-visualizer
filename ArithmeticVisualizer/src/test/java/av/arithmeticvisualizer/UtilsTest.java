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
    static double[][] A;
    static double[][] B;
    static double[][] C;
    static double[][] arrayThree;
    static double[][] arrayFour;

    public UtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        A = new double[][]{{1.1, 2.2, 3.3}, {1.0, 2.0, 3.0}};
        B = new double[][]{{1.0, 1.0, 1.0}, {1.0, 1.0, 1.0}};
        C = new double[][]{{2.0, 3.0}, {4.0, 5.0}, {6.0, 7.0}};
        arrayThree = new double[][]{{3.0}};
        arrayFour = new double[][]{{4.0}};
        
       

        scalarAdditionResult = Utils.addArrays(
                arrayThree, arrayFour);

        matrixAdditionResult = Utils.addArrays(A, B);

        matrixMultiplicationResult = Utils.matrixMultiply(A, C);
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
        assertEquals(21.0, Utils.multiplyArrays(arrayThree, C)[2][1], .001);
    }
    
    @Test
    public void scalarMultiplicationFromRightWorks() {
        assertEquals(21.0, Utils.multiplyArrays(C, arrayThree)[2][1], .001);
    }
    
    
    

}
