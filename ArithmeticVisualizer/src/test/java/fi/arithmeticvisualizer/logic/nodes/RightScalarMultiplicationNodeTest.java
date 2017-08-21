package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.BadArrayException;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import static fi.arithmeticvisualizer.logic.nodes.LeftScalarMultiplicationNodeTest.av1;
import static fi.arithmeticvisualizer.logic.nodes.LeftScalarMultiplicationNodeTest.bn1;
import fi.arithmeticvisualizer.logic.visualization.ActivationPattern;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RightScalarMultiplicationNodeTest {

    static BinaryNode bn1;
    static BinaryNode bn2;
    static BinaryNode bn3;
    static ArrayValue av1;
    static ArrayValue av2;
    static ArrayValue scalarTwo;
    static ArrayValue scalarNegativeTwo;

    public RightScalarMultiplicationNodeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws BadArrayException {
        av1 = new ArrayValue("1 2 3; 4 5 6");
        av2 = new ArrayValue("1 1 1; -1 -1 -1");
        scalarTwo = new ArrayValue("2");
        scalarNegativeTwo = new ArrayValue("-2");
        bn1 = new RightScalarMultiplicationNode(av1, scalarTwo);
        bn2 = new RightScalarMultiplicationNode(av1, scalarNegativeTwo);
        bn3 = new RightScalarMultiplicationNode(av2, scalarTwo);
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
    public void constructionFromNodesWorks() throws BadArrayException {
        BinaryNode bn = new RightScalarMultiplicationNode(bn1, new ValueNode(new ArrayValue("-17.0")));
    }

    @Test
    public void getSymbolWorks() {
        assertEquals("*", bn1.getSymbol());
    }

    @Test
    public void evaluateWorks() {
        ArrayValue result = bn1.evaluate();
        assertEquals(new Dimensions(2, 3), result.getDimensions());
        assertEquals(12, result.getElement(1, 2), .001);
    }

    @Test
    public void getRightWorks() {
        assertEquals(2, bn1.getRight().evaluate().getElement(0, 0), .001);
    }

    @Test
    public void getLeftWorks() {
        assertEquals(6, bn1.getLeft().evaluate().getElement(1, 2), .001);
    }

    @Test
    public void getActivationPatternWorks() {
        assertEquals(ActivationPattern.RIGHTSCALARMULTIPLICATION, bn1.getActivationPattern());
    }

    @Test
    public void validInputDimensionsWorks() {
        assertEquals(true, bn1.validImputDimensions());
        assertEquals(false, new LeftScalarMultiplicationNode(av1, av1).validImputDimensions());
    }

    @Test
    public void getSubOperationStringsWorks() {
        ArrayList<String> strings = bn1.getSubOperationStrings();
        assertEquals(6, strings.size());
        assertEquals("6.0 * 2.0 = 12.0", strings.get(5));

        strings = bn3.getSubOperationStrings();
        assertEquals("-1.0 * 2.0 = -2.0", strings.get(3));
    }

}
