package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.WrongShapeException;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import fi.arithmeticvisualizer.logic.nodes.ValueNode;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValueNodeTest {

    private ValueNode vThreeXTwo;
    private ValueNode vJagged;
    private ValueNode scalar;
    private double[][] threeXTwo;
    private double[][] jagged;

    public ValueNodeTest() throws WrongShapeException {
        threeXTwo = new double[][]{{1, 2}, {3, 4}, {5, 6}};
        vThreeXTwo = new ValueNode(threeXTwo);
        
        jagged = new double[][]{{1, 2}, {3}, {5, 6}};
        
        scalar = new ValueNode(42.0);
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
        ArrayValue val = vThreeXTwo.evaluate();
        assertEquals(3, val.getDimensions().getM());
        assertEquals(2, val.getDimensions().getN());
    }
    
    @Test
    public void scalarConstructorWorks() throws WrongShapeException {
        ArrayValue val = scalar.evaluate();
        assertEquals(42.0, val.getValue()[0][0], 0.001);
        assertEquals(true, val.isScalar());
        assertEquals(new Dimensions(1, 1), val.getDimensions());
    }
}
