package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.WrongShapeException;
import fi.arithmeticvisualizer.logic.nodes.UnaryNode;
import fi.arithmeticvisualizer.logic.nodes.ValueNode;
import static fi.arithmeticvisualizer.logic.utils.NodeFunctions.negate;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UnaryNodeTest {

    private ValueNode v1;
    private UnaryNode u1;

    public UnaryNodeTest() {
        double[][] array = new double[][]{{1, 2, 3}, {4, 5, 6}};
        v1 = new ValueNode(array);
        u1 = new UnaryNode(negate, v1);
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
    public void negationWorks() throws WrongShapeException {
        assertEquals(-4.0, u1.evaluate().getValue()[1][0], 0.001);

    }
}
