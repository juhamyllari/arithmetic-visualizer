package fi.arithmeticvisualizer.logic.visualization;

import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BooleanMaskTest {

    public BooleanMaskTest() {
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
    public void constructionByDimensionsWorks() {
        BooleanMask bm = new BooleanMask(2, 3);
        assertEquals(2, bm.getMask().length);
        assertEquals(3, bm.getMask()[0].length);
    }

    @Test
    public void constructionByDimensionsObjectWorks() {
        BooleanMask bm = new BooleanMask(new Dimensions(5, 6));
        assertEquals(5, bm.getMask().length);
        assertEquals(6, bm.getMask()[0].length);
    }
    
}
