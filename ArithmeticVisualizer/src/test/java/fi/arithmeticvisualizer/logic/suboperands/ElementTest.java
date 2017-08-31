package fi.arithmeticvisualizer.logic.suboperands;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ElementTest {

    public ElementTest() {
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
        double value = -1.68e5;
        int m = 2;
        int n = 3;
        Element element = new Element(m, n, value);

        assertEquals(m, element.getM());
        assertEquals(n, element.getN());
        assertEquals(value, element.getValue(), 0.0);
    }
}
