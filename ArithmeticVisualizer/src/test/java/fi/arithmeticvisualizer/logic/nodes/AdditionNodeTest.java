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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdditionNodeTest {

    static BinaryNode bn1;
    static BinaryNode bn2;
    static ValueNode v1;
    static ValueNode v2;
    static ValueNode v3;

    public AdditionNodeTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        v1 = new ValueNode(new double[][]{{1.1, 2.2, 3.3}, {1.0, 2.0, 3.0}});
        v2 = new ValueNode(new double[][]{{1.0, 1.0, -1.0}, {1.0, 1.0, 1.0}});
        v3 = new ValueNode(new double[][]{{2.0, 3.0}, {4.0, 5.0}, {6.0, 7.0}});
        bn1 = new AdditionNode(v1, v2);
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
    public void constructionFromArrayValuesWorks() throws BadArrayException {
        bn1 = new AdditionNode(new ArrayValue("1 2; 3 4"), new ArrayValue("1 1; 1 1"));
        assertEquals(new Dimensions(2, 2), bn1.evaluate().getDimensions());
        assertEquals(5, bn1.evaluate().getValue()[1][1], .001);
    }
    
    @Test
    public void outDimsWorks() throws BadArrayException {
        bn1 = new AdditionNode(new ArrayValue("1 2; 3 4"), new ArrayValue("1 1; 1 1"));
        assertEquals(new Dimensions(2, 2), bn1.outDimensions());
    }
    
    @Test
    public void getLeftWorks() throws BadArrayException {
        bn1 = new AdditionNode(new ArrayValue("1 2; 3.14 4"), new ArrayValue("1 1; 1 1"));
        assertEquals(3.14, bn1.getLeft().evaluate().getElement(1, 0), .001);
    }
    
    @Test
    public void getRightWorks() throws BadArrayException {
        bn1 = new AdditionNode(new ArrayValue("1 2; 3.14 4"), new ArrayValue("1 1; 1 5e-9"));
        assertEquals(5e-9, bn1.getRight().evaluate().getElement(1, 1), .001);
    }
    
    @Test
    public void additionWorks() {
        bn1 = new AdditionNode(v1, v2);
        assertEquals(2.3, bn1.evaluate().getValue()[0][2], .001);
    }
    
    @Test
    public void additionNodeGivesCorrectSymbol() {
        assertEquals("+", bn1.getSymbol());
    }
    
    @Test
    public void validInputDimensionsWorks() {
        bn2 = new AdditionNode(v1, v3);
        assertEquals(true, bn1.validImputDimensions());
        assertEquals(false, bn2.validImputDimensions());
    }
    
    @Test
    public void getFrameSequenceWorks() {
        bn1 = new AdditionNode(v1, v2);
        FrameSequence sequence = bn1.getFrameSequence(ELEMENTWISE);
        Frame frame0 = sequence.getFrame(0);
        Frame frame2 = sequence.getFrame(2);
        String expected0 = formatDouble(1.1) + " + " + formatDouble(1.0)+ " = " + formatDouble(2.1);
        String expected2 = formatDouble(3.3) + " + (" + formatDouble(-1.0) + ") = " + formatDouble(2.3);
        assertEquals(6, sequence.getLength());
        assertEquals(expected0, frame0.getSubOperationString());
        assertEquals(expected2, frame2.getSubOperationString());
    }
    
}
