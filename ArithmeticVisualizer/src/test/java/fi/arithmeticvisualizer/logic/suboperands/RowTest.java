package fi.arithmeticvisualizer.logic.suboperands;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RowTest {

    public RowTest() {
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
    public void gettersWork() {
        double[] rowVector = new double[]{1, -2, 10.4e-6};
        Row row = new Row(3, rowVector);

        assertEquals(3, row.getRowIndex());
        assertArrayEquals(rowVector, row.getRowVector(), 0.0);
    }
}
