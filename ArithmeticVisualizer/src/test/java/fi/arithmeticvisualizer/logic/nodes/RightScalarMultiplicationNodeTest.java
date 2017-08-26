package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.BadArrayException;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import fi.arithmeticvisualizer.gui.FramePattern;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.EvaluationStyle.ELEMENTWISE;
import static fi.arithmeticvisualizer.logic.nodes.Node.formatDouble;
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
    public void validInputDimensionsWorks() {
        assertEquals(true, bn1.validImputDimensions());
        assertEquals(false, new LeftScalarMultiplicationNode(av1, av1).validImputDimensions());
    }

    @Test
    public void getOperationPatternWorks() {
        assertEquals(FramePattern.RIGHTSCALARMULTIPLICATIONELEMENTWISE, bn1.getOperationPattern(ELEMENTWISE));
    }

    @Test
    public void getFrameSequenceWorks() {
        FrameSequence sequence = bn1.getFrameSequence(ELEMENTWISE);
        assertEquals(6, sequence.getLength());
    }

    @Test
    public void frameStringWorks() {
        bn1.evaluate();
        String expected = formatDouble(1.0) + " * " + formatDouble(2.0) + " = " + formatDouble(2.0);
        assertEquals(expected, bn1.frameString(BinaryNode.FrameStringPattern.ELEMENT_BY_ELEMENT, 0, 0));
    }

}
