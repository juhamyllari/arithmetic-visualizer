package fi.arithmeticvisualizer.logic.nodes;

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

    public BinaryNodeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws WrongShapeException {

        v1 = new ValueNode(new double[][]{{1.1, 2.2, 3.3}, {1.0, 2.0, 3.0}});
        v2 = new ValueNode(new double[][]{{1.0, 1.0, 1.0}, {1.0, 1.0, 1.0}});
        v3 = new ValueNode(new double[][]{{2.0, 3.0}, {4.0, 5.0}, {6.0, 7.0}});

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
