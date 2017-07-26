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
     public void hello() {}
     
     @Test
     public void addingTwoScalarsGivesCorrectResult() throws DimensionMismatchException {
         Node n = new AdditionNode(new ArrayNode(23.0), new ArrayNode(-28.0));
         double result = n.eval()[0][0];
         assertEquals(-5.0, result, .001);
     }
}
