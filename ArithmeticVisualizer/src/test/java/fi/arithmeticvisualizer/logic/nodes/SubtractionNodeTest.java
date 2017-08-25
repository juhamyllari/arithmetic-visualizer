/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.BadArrayException;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import fi.arithmeticvisualizer.gui.OperationPattern;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.EvaluationStyle.ELEMENTWISE;
import static fi.arithmeticvisualizer.logic.nodes.Node.formatDouble;
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
    public void validInputDimensionsWorks() {
        bn1 = new SubtractionNode(v1, v2);
        bn2 = new SubtractionNode(v1, v3);
        assertEquals(true, bn1.validImputDimensions());
        assertEquals(false, bn2.validImputDimensions());
    }

    @Test
    public void getOperationPatternWorks() {
        bn1 = new SubtractionNode(v1, v2);
        assertEquals(OperationPattern.SUBTRACTIONELEMENTWISE, bn1.getOperationPattern(ELEMENTWISE));
    }

    @Test
    public void getFrameSequenceWorks() {
        bn1 = new SubtractionNode(v1, v2);
        FrameSequence sequence = bn1.getFrameSequence(ELEMENTWISE);
        assertEquals(6, sequence.getLength());
    }

    @Test
    public void frameStringWorks() {
        SubtractionNode sn1 = new SubtractionNode(v1, v2);
        sn1.evaluate();
        String expected1 = formatDouble(1.1) + " - " + formatDouble(1.0) + " = " + formatDouble(0.1);
        String expected2 = formatDouble(3.3) + " - (" + formatDouble(-1.0) + ") = " + formatDouble(4.3);
        assertEquals(expected1, sn1.frameString(BinaryNode.FrameStringPattern.ELEMENT_BY_ELEMENT, 0, 0));
        assertEquals(expected2, sn1.frameString(BinaryNode.FrameStringPattern.ELEMENT_BY_ELEMENT, 0, 2));
    }

}
