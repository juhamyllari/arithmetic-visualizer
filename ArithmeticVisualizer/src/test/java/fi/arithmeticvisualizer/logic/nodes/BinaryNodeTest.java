package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.WrongShapeException;
import static fi.arithmeticvisualizer.logic.utils.NodeFunctions.addition;
import static fi.arithmeticvisualizer.logic.utils.NodeFunctions.multiplication;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryNodeTest {

    static BinaryNode bn1;
    static BinaryNode bn2;
    static ValueNode v1;
    static ValueNode v2;
    static ValueNode v3;

    public BinaryNodeTest() {
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
    public void additionWorks() {
        bn1 = new BinaryNode(v1, v2, addition);
        try {
            assertEquals(4.3, bn1.evaluate().getValue()[0][2], .001);
        } catch (WrongShapeException ex) {
            Logger.getLogger(BinaryNodeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void multiplicationWorks() {
        bn1 = new BinaryNode(v1, v3, multiplication);
        try {
            assertEquals(37.4, bn1.evaluate().getValue()[0][1], .001);
        } catch (WrongShapeException ex) {
            Logger.getLogger(BinaryNodeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void multiplicationNodeGivesCorrectSymbol() {
        bn1 = new BinaryNode(v1, v3, multiplication);
        assertEquals('*', bn1.getSymbol());
    }

    @Test
    public void toStringWorks() {
        bn1 = new BinaryNode(v1, v3, multiplication);
        assertEquals("[[1.1, 2.2, 3.3], [1.0, 2.0, 3.0]] * [[2.0, 3.0], [4.0, 5.0], [6.0, 7.0]]", bn1.toString());
    }

    @Test
    public void toStringGivesCorrectMessage() {
        double[][] array = new double[][]{{1}, {2}};
        bn1 = new BinaryNode(v1, new BinaryNode(new ValueNode(array), new ValueNode(23), addition), addition);
        assertEquals("Invalid Node", bn1.toString());
    }
}
