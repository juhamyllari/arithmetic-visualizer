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
    public void arrayConstructedDimsWork() {
        double[][] array = new double[][]{{1, 2}, {3, 4}, {5, 6}};
        Dimensions dims = new Dimensions(array);
        assertEquals(3, dims.getM());
        assertEquals(2, dims.getN());
    }

    @Test
    public void toStringWorks() {
        double[][] array = new double[][]{{1, 2}, {3, 4}, {5, 6}};
        Dimensions dims = new Dimensions(array);
        assertEquals("(3, 2)", dims.toString());
    }
    
    @Test
    public void equalsWorks() {
        assertEquals(new Dimensions(23, 24), new Dimensions(23, 24));
    }
}
