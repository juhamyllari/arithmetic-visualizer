package fi.arithmeticvisualizer.logic.suboperands;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ColumnTest {

    public ColumnTest() {
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
        double[] columnVector = new double[] {1, -2, 10.4e-6};
        Column column = new Column(4, columnVector);
        
        assertEquals(4, column.getColumnIndex());
        assertArrayEquals(columnVector, column.getColumnVector(), 0.0);
    }
}
