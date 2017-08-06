package av.arithmeticvisualizer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExpressionTest {
    
    private Expression exp;
    
    public ExpressionTest() {
        
        exp = new Expression(new ValueNode(23.0));
        
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
    public void evaluateReturnsCorrectScalar() throws WrongShapeException {
        Value val = exp.evaluate();
        assertEquals(23.0, val.getValue()[0][0], 0.0001);
    }
}
