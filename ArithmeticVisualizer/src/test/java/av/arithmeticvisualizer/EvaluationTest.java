package av.arithmeticvisualizer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class EvaluationTest {

    public EvaluationTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
    }

    @Test
    public void addingTwoScalarsGivesCorrectResult() throws WrongShapeException {
        Node n = new AdditionNode(new ValueNode(23.0), new ValueNode(-28.0));
        double[][] result = n.eval().getValue();
        assertEquals(-5.0, result[0][0], .001);
        assertEquals(1, result.length);
        assertEquals(1, result[0].length);
    }
}
