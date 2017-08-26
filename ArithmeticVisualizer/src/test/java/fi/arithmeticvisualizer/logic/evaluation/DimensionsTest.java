package fi.arithmeticvisualizer.logic.evaluation;

import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DimensionsTest {

    public DimensionsTest() {
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
    public void dimsReturnsCorrectDimensions() {
        Dimensions dims = new Dimensions(23, 42);
        assertEquals(23, dims.getM());
        assertEquals(42, dims.getN());
    }

    @Test
    public void toStringWorks() {
        Dimensions dims = new Dimensions(3, 2);
        assertEquals("(3, 2)", dims.toString());
    }
    
    @Test
    public void equalsWorks() {
        Dimensions dims1 = new Dimensions(2, 4);
        Dimensions dims2 = dims1;
        Dimensions dims3 = new Dimensions(3, 4);
        Dimensions dims4 = new Dimensions(2, 5);
        assertEquals(dims1, dims2);
        assertNotEquals(dims1, null);
        assertNotEquals(dims1, dims3);
        assertNotEquals(dims1, dims4);
        assertNotEquals(dims1, "(2, 4)");
        assertEquals(new Dimensions(23, 24), new Dimensions(23, 24));
    }
    
    @Test
    public void hashCodeWorks() {
        Dimensions dims1 = new Dimensions(2, 4);
        Dimensions dims2 = new Dimensions(2, 4);
        assertEquals(dims1.hashCode(), dims2.hashCode());
    }
}
