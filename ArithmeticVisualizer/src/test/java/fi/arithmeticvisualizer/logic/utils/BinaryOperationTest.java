package fi.arithmeticvisualizer.logic.utils;

import static fi.arithmeticvisualizer.logic.utils.OperationSelector.addition;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryOperationTest {

    public BinaryOperationTest() {
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
    public void defaultConstructorReturnsBinaryOperation() {
        assertEquals(BinaryOperation.class, new BinaryOperation().getClass());
    }
    
    @Test
    public void getSymbolOnAdditionReturnsPlus() {
        assertEquals("+", addition.getSymbol());
    }
}
