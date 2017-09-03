package fi.arithmeticvisualizer.logic.frames;

import fi.arithmeticvisualizer.logic.suboperands.Column;
import fi.arithmeticvisualizer.logic.suboperands.Element;
import fi.arithmeticvisualizer.logic.suboperands.Row;
import fi.arithmeticvisualizer.logic.nodes.Node;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RowDotColumnFrameTest {

    public RowDotColumnFrameTest() {
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
    public void activationGettersWork() {
        double[] row = new double[]{1, 1};
        double[] column = new double[]{2, 2};
        Row left = new Row(1, row);
        Column right = new Column(1, column);
        RowDotColumnFrame frame = new RowDotColumnFrame(left, right);

        BooleanMask bm = new BooleanMask(2, 2);
        frame.getLeftActivation().set(bm);
        Assert.assertEquals(false, bm.getMask()[0][0]);
        Assert.assertEquals(false, bm.getMask()[0][1]);
        Assert.assertEquals(true, bm.getMask()[1][0]);
        Assert.assertEquals(true, bm.getMask()[1][1]);

        frame.getRightActivation().set(bm);
        Assert.assertEquals(false, bm.getMask()[0][0]);
        Assert.assertEquals(true, bm.getMask()[0][1]);
        Assert.assertEquals(false, bm.getMask()[1][0]);
        Assert.assertEquals(true, bm.getMask()[1][1]);

        frame.getResultActivation().set(bm);
        Assert.assertEquals(false, bm.getMask()[0][0]);
        Assert.assertEquals(false, bm.getMask()[0][1]);
        Assert.assertEquals(false, bm.getMask()[1][0]);
        Assert.assertEquals(true, bm.getMask()[1][1]);

        frame.getResultShown().set(bm);
        Assert.assertEquals(true, bm.getMask()[0][0]);
        Assert.assertEquals(true, bm.getMask()[0][1]);
        Assert.assertEquals(true, bm.getMask()[1][0]);
        Assert.assertEquals(true, bm.getMask()[1][1]);

    }

    @Test
    public void getSubOperationStringWorks() {
        double[] row = new double[]{1, 2};
        double[] column = new double[]{10, 20};
        Row left = new Row(1, row);
        Column right = new Column(1, column);
        RowDotColumnFrame frame = new RowDotColumnFrame(left, right);
        
        String expected = Node.formatDouble(1) 
                + " * "
                + Node.formatDouble(10)
                + " + "
                + Node.formatDouble(2)
                + " * "
                + Node.formatDouble(20)
                + " = "
                + Node.formatDouble(50);
        assertEquals(expected, frame.getSubOperationString());
    }
}
