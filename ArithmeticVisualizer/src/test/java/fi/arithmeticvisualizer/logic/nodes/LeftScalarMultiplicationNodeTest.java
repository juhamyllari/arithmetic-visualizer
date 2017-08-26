package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.Frame;
import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.BadArrayException;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.EvaluationStyle.ELEMENTWISE;
import static fi.arithmeticvisualizer.logic.nodes.Node.formatDouble;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LeftScalarMultiplicationNodeTest {

    static BinaryNode bn1;
    static BinaryNode bn2;
    static BinaryNode bn3;
    static ArrayValue av1;
    static ArrayValue av2;
    static ArrayValue scalarTwo;
    static ArrayValue scalarNegativeTwo;

    public LeftScalarMultiplicationNodeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws BadArrayException {
        av1 = new ArrayValue("1 2 3; 4 5 6");
        av2 = new ArrayValue("1 1 1; -1 -1 -1");
        scalarTwo = new ArrayValue("2");
        scalarNegativeTwo = new ArrayValue("-2");
        bn1 = new LeftScalarMultiplicationNode(scalarTwo, av1);
        bn2 = new LeftScalarMultiplicationNode(scalarNegativeTwo, av1);
        bn3 = new LeftScalarMultiplicationNode(scalarTwo, av2);
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
        BinaryNode bn = new LeftScalarMultiplicationNode(new ValueNode(new ArrayValue("-17.0")), bn1);
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
    public void getLeftWorks() {
        assertEquals(2, bn1.getLeft().evaluate().getElement(0, 0), .001);
    }

    @Test
    public void getRightWorks() {
        assertEquals(6, bn1.getRight().evaluate().getElement(1, 2), .001);
    }

    @Test
    public void validInputDimensionsWorks() {
        assertEquals(true, bn1.validImputDimensions());
        assertEquals(false, new LeftScalarMultiplicationNode(av1, av1).validImputDimensions());
    }
    
    @Test
    public void getFrameSequenceWorks() throws BadArrayException {
        bn1 = new LeftScalarMultiplicationNode(scalarTwo, new ArrayValue("1 2 -3; 4 5 6"));
        FrameSequence sequence = bn1.getFrameSequence(ELEMENTWISE);
        Frame frame0 = sequence.getFrame(0);
        Frame frame2 = sequence.getFrame(2);
        String expected0 = formatDouble(2.0) + " * " + formatDouble(1.0)+ " = " + formatDouble(2.0);
        String expected2 = formatDouble(2.0) + " * (" + formatDouble(-3.0) + ") = " + formatDouble(-6.0);
        assertEquals(6, sequence.getLength());
        assertEquals(expected0, frame0.getSubOperationString());
        assertEquals(expected2, frame2.getSubOperationString());
    }

}
