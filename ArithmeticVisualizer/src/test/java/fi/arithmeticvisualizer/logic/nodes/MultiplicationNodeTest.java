package fi.arithmeticvisualizer.logic.nodes;

import static fi.arithmeticvisualizer.logic.nodes.AdditionNodeTest.bn1;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNodeTest.v1;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MultiplicationNodeTest {

    static BinaryNode bn1;
    static BinaryNode bn2;
    static ValueNode v1;
    static ValueNode v2;
    static ValueNode v3;

    public MultiplicationNodeTest() {
    }

    @BeforeClass
    public static void setUpClass() {

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
    public void multiplicationNodeGivesCorrectSymbol() {
        bn1 = new MatrixMultiplicationNode(v1, v3);
        assertEquals("*", bn1.getSymbol());
    }
}
