package av.arithmeticvisualizer.logic;

import av.arithmeticvisualizer.logic.WrongShapeException;
import av.arithmeticvisualizer.logic.NodeFunctions;
import av.arithmeticvisualizer.logic.BinaryNode;
import av.arithmeticvisualizer.logic.ValueNode;
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
        
        v1 = new ValueNode(new double[][] {{1.1, 2.2, 3.3}, {1.0, 2.0, 3.0}});
        v2 = new ValueNode(new double[][] {{1.0, 1.0, 1.0}, {1.0, 1.0, 1.0}});
        v3 = new ValueNode(new double[][] {{2.0, 3.0}, {4.0, 5.0}, {6.0, 7.0}});
        
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
     public void additionWorks() {
         bn1 = new BinaryNode(v1, v2, NodeFunctions.add);
        try {
            assertEquals(4.3, bn1.evaluate().getValue()[0][2], .001);
        } catch (WrongShapeException ex) {
            Logger.getLogger(BinaryNodeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     @Test
     public void multiplicationWorks() {
         bn1 = new BinaryNode(v1, v3, NodeFunctions.multiply);
        try {
            assertEquals(37.4, bn1.evaluate().getValue()[0][1], .001);
        } catch (WrongShapeException ex) {
            Logger.getLogger(BinaryNodeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
