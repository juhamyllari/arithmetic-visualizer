package fi.arithmeticvisualizer.logic;

//import av.arithmeticvisualizer.logic.WrongShapeException;
//import av.arithmeticvisualizer.logic.Value;
//import av.arithmeticvisualizer.logic.ValueNode;
//import av.arithmeticvisualizer.logic.Expression;
//import av.arithmeticvisualizer.logic.Expression;
//import av.arithmeticvisualizer.logic.Value;
//import av.arithmeticvisualizer.logic.ValueNode;
//import av.arithmeticvisualizer.logic.WrongShapeException;
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
