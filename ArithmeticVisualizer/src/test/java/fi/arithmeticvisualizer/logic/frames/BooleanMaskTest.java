package fi.arithmeticvisualizer.logic.frames;

import fi.arithmeticvisualizer.logic.frames.BooleanMask;
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

    @Test
    public void setElementWorks() {
        BooleanMask bm = new BooleanMask(new Dimensions(2, 2));
        assertEquals(false, bm.getMask()[0][0]);
        bm.setAdditionalElement(0, 0);
        assertEquals(true, bm.getMask()[0][0]);
    }

    @Test
    public void setAllWorks() {
        BooleanMask bm = new BooleanMask(new Dimensions(2, 2));
        assertEquals(false, bm.getMask()[0][0]);
        bm.setAll();
        assertEquals(true, bm.getMask()[0][0]);
        assertEquals(true, bm.getMask()[0][1]);
        assertEquals(true, bm.getMask()[1][0]);
        assertEquals(true, bm.getMask()[1][1]);
    }

    @Test
    public void setRowWorks() {
        BooleanMask bm = new BooleanMask(new Dimensions(2, 2));
        assertEquals(false, bm.getMask()[0][0]);
        bm.setRow(1);
        assertEquals(false, bm.getMask()[0][0]);
        assertEquals(false, bm.getMask()[0][1]);
        assertEquals(true, bm.getMask()[1][0]);
        assertEquals(true, bm.getMask()[1][1]);
    }

    @Test
    public void setColumnWorks() {
        BooleanMask bm = new BooleanMask(new Dimensions(2, 2));
        assertEquals(false, bm.getMask()[0][0]);
        bm.setColumn(1);
        assertEquals(false, bm.getMask()[0][0]);
        assertEquals(true, bm.getMask()[0][1]);
        assertEquals(false, bm.getMask()[1][0]);
        assertEquals(true, bm.getMask()[1][1]);
    }

    @Test
    public void setUpToByRowWorks() {
        BooleanMask bm = new BooleanMask(new Dimensions(2, 2));

        bm.setUpToByRow(0, 1);
        assertEquals(true, bm.getMask()[0][0]);
        assertEquals(true, bm.getMask()[0][1]);
        assertEquals(false, bm.getMask()[1][0]);
        assertEquals(false, bm.getMask()[1][1]);

        bm.setUpToByRow(1, 0);
        assertEquals(true, bm.getMask()[0][0]);
        assertEquals(true, bm.getMask()[0][1]);
        assertEquals(true, bm.getMask()[1][0]);
        assertEquals(false, bm.getMask()[1][1]);

        bm.setUpToByRow(1, 1);
        assertEquals(true, bm.getMask()[0][0]);
        assertEquals(true, bm.getMask()[0][1]);
        assertEquals(true, bm.getMask()[1][0]);
        assertEquals(true, bm.getMask()[1][1]);

        bm.setUpToByRow(0, 0);
        assertEquals(true, bm.getMask()[0][0]);
        assertEquals(false, bm.getMask()[0][1]);
        assertEquals(false, bm.getMask()[1][0]);
        assertEquals(false, bm.getMask()[1][1]);
    }

    @Test
    public void setUpToByColumnWorks() {
        BooleanMask bm = new BooleanMask(new Dimensions(2, 2));
        bm.setUpToByColumn(1, 0);
        assertEquals(true, bm.getMask()[0][0]);
        assertEquals(false, bm.getMask()[0][1]);
        assertEquals(true, bm.getMask()[1][0]);
        assertEquals(false, bm.getMask()[1][1]);

        bm.setUpToByColumn(0, 1);
        assertEquals(true, bm.getMask()[0][0]);
        assertEquals(true, bm.getMask()[0][1]);
        assertEquals(true, bm.getMask()[1][0]);
        assertEquals(false, bm.getMask()[1][1]);

        bm.setUpToByColumn(1, 1);
        assertEquals(true, bm.getMask()[0][0]);
        assertEquals(true, bm.getMask()[0][1]);
        assertEquals(true, bm.getMask()[1][0]);
        assertEquals(true, bm.getMask()[1][1]);

        bm.setUpToByColumn(0, 0);
        assertEquals(true, bm.getMask()[0][0]);
        assertEquals(false, bm.getMask()[0][1]);
        assertEquals(false, bm.getMask()[1][0]);
        assertEquals(false, bm.getMask()[1][1]);

    }

    @Test
    public void clearAllWorks() {
        BooleanMask bm = new BooleanMask(new Dimensions(2, 2));
        assertEquals(false, bm.getMask()[0][1]);
        bm.setAll();
        assertEquals(true, bm.getMask()[0][1]);
        bm.clearAll();
        assertEquals(false, bm.getMask()[0][0]);
        assertEquals(false, bm.getMask()[0][1]);
        assertEquals(false, bm.getMask()[1][0]);
        assertEquals(false, bm.getMask()[1][1]);
    }

}
