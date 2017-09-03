package fi.arithmeticvisualizer.logic.frames;

import fi.arithmeticvisualizer.logic.suboperands.Element;
import java.util.function.DoubleBinaryOperator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ElementWithElementFrameTest {

    private DoubleBinaryOperator plus = (double left, double right) -> left + right;
    private DoubleBinaryOperator times = (double left, double right) -> left * right;

    public ElementWithElementFrameTest() {
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
    public void convenienceConstructorWorks() {
        Element left = new Element(0, 0, 12.3);
        Element right = new Element(0, 0, -2.1);
        Frame frame = new ElementWithElementFrame(plus, "+", left, right);
    }

    @Test
    public void activationGettersWork() {
        Element left = new Element(1, 0, 12.3);
        Element right = new Element(1, 0, -2.1);
        ElementWithElementFrame frame = new ElementWithElementFrame(plus, "+", left, right);
        
        BooleanMask bm = new BooleanMask(2, 2);
        frame.getLeftActivation().set(bm);
        Assert.assertEquals(false, bm.getMask()[0][0]);
        Assert.assertEquals(false, bm.getMask()[0][1]);
        Assert.assertEquals(true, bm.getMask()[1][0]);
        Assert.assertEquals(false, bm.getMask()[1][1]);
        
        frame.getRightActivation().set(bm);
        Assert.assertEquals(false, bm.getMask()[0][0]);
        Assert.assertEquals(false, bm.getMask()[0][1]);
        Assert.assertEquals(true, bm.getMask()[1][0]);
        Assert.assertEquals(false, bm.getMask()[1][1]);
        
        frame.getResultActivation().set(bm);
        Assert.assertEquals(false, bm.getMask()[0][0]);
        Assert.assertEquals(false, bm.getMask()[0][1]);
        Assert.assertEquals(true, bm.getMask()[1][0]);
        Assert.assertEquals(false, bm.getMask()[1][1]);
        
        frame.getResultShown().set(bm);
        Assert.assertEquals(true, bm.getMask()[0][0]);
        Assert.assertEquals(true, bm.getMask()[0][1]);
        Assert.assertEquals(true, bm.getMask()[1][0]);
        Assert.assertEquals(false, bm.getMask()[1][1]);
        
        frame.setShowRowwise(false);
        frame.getResultShown().set(bm);
        Assert.assertEquals(true, bm.getMask()[0][0]);
        Assert.assertEquals(false, bm.getMask()[0][1]);
        Assert.assertEquals(true, bm.getMask()[1][0]);
        Assert.assertEquals(false, bm.getMask()[1][1]);
        
    }
}
