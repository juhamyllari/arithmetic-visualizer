package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValueNodeTest {

    private ValueNode vThreeXTwo;
    private double[][] threeXTwo;
    private double[][] jagged;

    public ValueNodeTest() {
        threeXTwo = new double[][]{{1, 2}, {3, 4}, {5, 6}};
        vThreeXTwo = new ValueNode(threeXTwo);
        
        jagged = new double[][]{{1, 2}, {3}, {5, 6}};
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
    public void valueNodeGivesArrayOfCorrectDimension() {
        ArrayValue val = vThreeXTwo.evaluate();
        assertEquals(3, val.getDimensions().getM());
        assertEquals(2, val.getDimensions().getN());
    }
}
