package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.BadArrayException;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import fi.arithmeticvisualizer.logic.evaluation.WrongShapeException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BinaryNodeTest {

    static BinaryNode bn1;
    static BinaryNode bn2;
    static ValueNode v1;
    static ValueNode v2;
    static ValueNode v3;
    static ArrayValue av1;

    public BinaryNodeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws WrongShapeException, BadArrayException {

        v1 = new ValueNode(new double[][]{{1.1, 2.2, 3.3}, {1.0, 2.0, 3.0}});
        v2 = new ValueNode(new double[][]{{1.0, 1.0, 1.0}, {1.0, 1.0, 1.0}});
        v3 = new ValueNode(new double[][]{{2.0, 3.0}, {4.0, 5.0}, {6.0, 7.0}});
        av1 = new ArrayValue("1 2 3; 4 5 6");

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
    public void createBinaryNodeCreatesAdditionNodes() {
        bn1 = BinaryNode.createBinaryNode(av1, av1, "+");
        assertEquals(AdditionNode.class, bn1.getClass());
        assertEquals(av1.getDimensions(), bn1.outDimensions());
    }
    
    @Test
    public void createBinaryNodeCreatesSubtractionNodes() {
        bn1 = BinaryNode.createBinaryNode(av1, av1, "-");
        assertEquals(SubtractionNode.class, bn1.getClass());
        assertEquals(av1.getDimensions(), bn1.outDimensions());
    }
    
    @Test
    public void createBinaryNodeCreatesMatrixMultiplicationNodes() {
        bn1 = BinaryNode.createBinaryNode(v1.evaluate(), v3.evaluate(), "*");
        assertEquals(MatrixMultiplicationNode.class, bn1.getClass());
        assertEquals(new Dimensions(2, 2), bn1.outDimensions());
    }
    
    @Test
    public void createBinaryNodeCreatesLeftScalarMultiplicationNodes() throws BadArrayException {
        bn1 = BinaryNode.createBinaryNode(new ArrayValue("-3.14"), v3.evaluate(), "*");
        assertEquals(LeftScalarMultiplicationNode.class, bn1.getClass());
        assertEquals(new Dimensions(3, 2), bn1.outDimensions());
    }
    
    @Test
    public void createBinaryNodeCreatesRightScalarMultiplicationNodes() throws BadArrayException {
        bn1 = BinaryNode.createBinaryNode(v3.evaluate(), new ArrayValue("-3.14"), "*");
        assertEquals(RightScalarMultiplicationNode.class, bn1.getClass());
        assertEquals(new Dimensions(3, 2), bn1.outDimensions());
    }
    
    @Test
    public void subtractionWorks() throws WrongShapeException {
        bn1 = new SubtractionNode(v1, v2);
        assertEquals(1.2, bn1.evaluate().getValue()[0][1], .001);
    }

    @Test
    public void multiplicationWorks() {
        bn1 = new MatrixMultiplicationNode(v1, v3);
        assertEquals(37.4, bn1.evaluate().getValue()[0][1], .001);
    }

    @Test
    public void multiplicationNodeGivesCorrectSymbol() {
        bn1 = new MatrixMultiplicationNode(v1, v3);
        assertEquals("*", bn1.getSymbol());
    }

//    @Test
//    public void toStringWorks() {
//        bn1 = new MultiplicationNode(v1, v3);
//        assertEquals("[[1.1, 2.2, 3.3], [1.0, 2.0, 3.0]] * [[2.0, 3.0], [4.0, 5.0], [6.0, 7.0]]", bn1.toString());
//    }
//
//    @Test
//    public void toStringGivesCorrectMessage() throws WrongShapeException {
//        double[][] array = new double[][]{{1}, {2}};
//        bn1 = new OldBinaryNode(v1, new OldBinaryNode(new ValueNode(array), new ValueNode(23), addition), addition);
//        assertEquals("Invalid Node", bn1.toString());
//    }
}
