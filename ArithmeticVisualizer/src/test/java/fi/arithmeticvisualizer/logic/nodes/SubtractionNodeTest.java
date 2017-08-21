/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.BadArrayException;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import static fi.arithmeticvisualizer.logic.nodes.AdditionNodeTest.v1;
import fi.arithmeticvisualizer.logic.visualization.ActivationPattern;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SubtractionNodeTest {

    static BinaryNode bn1;
    static BinaryNode bn2;
    static ValueNode v1;
    static ValueNode v2;
    static ValueNode v3;

    public SubtractionNodeTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        v1 = new ValueNode(new double[][]{{1.1, 2.2, 3.3}, {1.0, 2.0, 3.0}});
        v2 = new ValueNode(new double[][]{{1.0, 1.0, -1.0}, {1.0, 1.0, 1.0}});
        v3 = new ValueNode(new double[][]{{2.0, 3.0}, {4.0, 5.0}, {6.0, 7.0}});
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
    public void constructionFromArrayValuesWorks() throws BadArrayException {
        bn1 = new SubtractionNode(new ArrayValue("1 2; 3 4"), new ArrayValue("1 1; 1 1"));
        assertEquals(new Dimensions(2, 2), bn1.evaluate().getDimensions());
        assertEquals(3, bn1.evaluate().getValue()[1][1], .001);
    }
    
    @Test
    public void outDimsWorks() throws BadArrayException {
        bn1 = new SubtractionNode(new ArrayValue("1 2; 3 4"), new ArrayValue("1 1; 1 1"));
        assertEquals(new Dimensions(2, 2), bn1.outDimensions());
    }
    
    @Test
    public void getLeftWorks() throws BadArrayException {
        bn1 = new SubtractionNode(new ArrayValue("1 2; 3.14 4"), new ArrayValue("1 1; 1 1"));
        assertEquals(3.14, bn1.getLeft().evaluate().getElement(1, 0), .001);
    }
    
    @Test
    public void getRightWorks() throws BadArrayException {
        bn1 = new SubtractionNode(new ArrayValue("1 2; 3.14 4"), new ArrayValue("1 1; 1 5e-9"));
        assertEquals(5e-9, bn1.getRight().evaluate().getElement(1, 1), .001);
    }
    
    @Test
    public void subtractionWorks() {
        bn1 = new SubtractionNode(v1, v2);
        assertEquals(4.3, bn1.evaluate().getValue()[0][2], .001);
    }
    
    @Test
    public void subtractionNodeGivesCorrectSymbol() {
        bn1 = new SubtractionNode(v1, v2);
        assertEquals("-", bn1.getSymbol());
    }
    
    @Test
    public void getActivationPatternWorks() {
        bn1 = new SubtractionNode(v1, v2);
        assertEquals(ActivationPattern.SUBTRACTION, bn1.getActivationPattern());
    }
    
    @Test
    public void getSubOperationStringsWorks() {
        bn1 = new SubtractionNode(v1, v2);
        ArrayList<String> strings = bn1.getSubOperationStrings();
        assertEquals(6, strings.size());
        assertEquals("1.1 - 1.0 = 0.1", strings.get(0));
        assertEquals("3.3 - (-1.0) = 4.3", strings.get(2));
        
    }
    
    @Test
    public void validInputDimensionsWorks() {
        bn1 = new SubtractionNode(v1, v2);
        bn2 = new SubtractionNode(v1, v3);
        assertEquals(true, bn1.validImputDimensions());
        assertEquals(false, bn2.validImputDimensions());
    }
    
}
