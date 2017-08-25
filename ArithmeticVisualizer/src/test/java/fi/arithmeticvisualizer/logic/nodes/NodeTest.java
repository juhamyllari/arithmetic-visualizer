package fi.arithmeticvisualizer.logic.nodes;

import static fi.arithmeticvisualizer.logic.nodes.Node.formatDouble;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {

    public NodeTest() {
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
    public void formatDoubleWorks() {
        String format = BinaryNode.SUBOPERATION_FORMAT;
        assertEquals(String.format(format, 0.0), formatDouble(0));
        assertEquals(String.format(format, -0.4), formatDouble(-0.4));
        assertEquals(String.format(format, 7.8e-12), formatDouble(7.8e-12));
    }
}
