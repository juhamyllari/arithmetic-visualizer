package av.arithmeticvisualizer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.omg.PortableServer.POAPackage.WrongAdapter;

public class ValueNodeTest {

    private ValueNode vThreeXTwo;
    private ValueNode vJagged;
    private double[][] threeXTwo;
    private double[][] jagged;

    public ValueNodeTest() {
        threeXTwo = new double[][]{{1, 2}, {3, 4}, {5, 6}};
        vThreeXTwo = new ValueNode(threeXTwo);
        
        jagged = new double[][]{{1, 2}, {3}, {5, 6}};
        vJagged = new ValueNode(jagged);
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
    public void valueNodeGivesArrayOfCorrectDimension() throws WrongShapeException {
        Value val = vThreeXTwo.evaluate();
        assertEquals(3, val.getM());
        assertEquals(2, val.getN());
    }
    
    @Test(expected = WrongShapeException.class)
    public void jaggedArrayGivesException() throws WrongShapeException {
        vJagged.evaluate();
    }
}
