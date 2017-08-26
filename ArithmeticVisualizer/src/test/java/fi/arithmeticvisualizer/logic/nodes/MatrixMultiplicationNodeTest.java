package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import fi.arithmeticvisualizer.gui.FramePattern;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.BadArrayException;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.EvaluationStyle.ELEMENTWISE;
import static fi.arithmeticvisualizer.logic.nodes.Node.formatDouble;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixMultiplicationNodeTest {

    static BinaryNode bn1;
    static BinaryNode bn2;
    static ValueNode v1;
    static ValueNode v2;
    static ValueNode v3;
    static ValueNode v4;

    public MatrixMultiplicationNodeTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        v1 = new ValueNode(new double[][]{{1.1, 2.2, 3.3}, {1.0, 2.0, 3.0}});
        v2 = new ValueNode(new double[][]{{1.0, 1.0, 1.0}, {1.0, 1.0, 1.0}});
        v3 = new ValueNode(new double[][]{{2.0, 3.0}, {4.0, 5.0}, {-6.0, 7.0}});
        v4 = new ValueNode(new double[][]{{1.0, 2.0, 3.0}, {2.0, 3.0, 4.0}});
        
        bn1 = new MatrixMultiplicationNode(v1, v3);
        bn2 = new MatrixMultiplicationNode(v4, v3);
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
    public void getLeftWorks() {
        assertEquals(new Dimensions(2, 3), bn1.getLeft().outDimensions());
        assertEquals(3.3, bn1.getLeft().evaluate().getElement(0, 2), .001);
    }
    
    @Test
    public void getRightWorks() {
        assertEquals(new Dimensions(3, 2), bn1.getRight().outDimensions());
        assertEquals(5.0, bn1.getRight().evaluate().getElement(1, 1), .001);
    }

    @Test
    public void multiplicationNodeGivesCorrectSymbol() {
        assertEquals("*", bn1.getSymbol());
    }
    
    @Test
    public void validInputDimensionsWorks() throws BadArrayException {
        ArrayValue av1 = new ArrayValue("1 2 3; 4 5 6");
        ArrayValue av2 = new ArrayValue("1 2; 3 4; 5 6");
        ArrayValue av3 = new ArrayValue("6");
        assertEquals(true, (new MatrixMultiplicationNode(av1, av2)).validImputDimensions());
        assertEquals(false, (new MatrixMultiplicationNode(av1, av1)).validImputDimensions());
        assertEquals(false, (new MatrixMultiplicationNode(av1, av3)).validImputDimensions());
        assertEquals(false, (new MatrixMultiplicationNode(av3, av1)).validImputDimensions());
    }
    
    @Test
    public void getOperationPatternWorks() {
        assertEquals(FramePattern.MATRIXMULTIPLICATIONELEMENTWISE, bn1.getOperationPattern(BinaryNode.EvaluationStyle.ELEMENTWISE));
    }
    
    @Test
    public void getFrameSequenceWorks() {
        FrameSequence sequence = bn1.getFrameSequence(ELEMENTWISE);
        assertEquals(4, sequence.getLength());
    }

    @Test
    public void frameStringWorks() throws BadArrayException {
        MatrixMultiplicationNode mmn1 = new MatrixMultiplicationNode(new ArrayValue("1 2; 3 4"), new ArrayValue("10 20; 30 -40"));
        MatrixMultiplicationNode mmn2 = new MatrixMultiplicationNode(new ArrayValue("1 2; 3 4"), new ArrayValue("10 20; 30 40"));
        mmn1.evaluate();
        mmn2.evaluate();
        
        String expected1 = formatDouble(3.0) 
                + " * " 
                + formatDouble(20.0) 
                + " + " + formatDouble(4.0) 
                + " * (" + formatDouble(-40.0) 
                + ") = " + formatDouble(-100.0);
        assertEquals(expected1, mmn1.frameString(BinaryNode.FrameStringPattern.ROW_BY_COLUMN, 1, 1));
        
        String expected2 = formatDouble(3.0) 
                + " * " 
                + formatDouble(20.0) 
                + " + " + formatDouble(4.0) 
                + " * " + formatDouble(40.0) 
                + " = " + formatDouble(220.0);
        assertEquals(expected2, mmn2.frameString(BinaryNode.FrameStringPattern.ROW_BY_COLUMN, 1, 1));
    }
    
}
