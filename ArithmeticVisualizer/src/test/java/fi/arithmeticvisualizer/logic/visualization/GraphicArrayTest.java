package fi.arithmeticvisualizer.logic.visualization;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.BadArrayException;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphicArrayTest {

    static GraphicArray array1;
    static GraphicArray array2;
    static GridPane grid1;
    static GridPane grid2;

    public GraphicArrayTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws BadArrayException {

        grid1 = new GridPane();
        grid2 = new GridPane();
        array1 = new GraphicArray(grid1, new ArrayValue("1 2; 3 4"));
        array2 = new GraphicArray(grid2, new ArrayValue("111111111.1 2; 3 4"));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void toInputStringWorks() {
        assertEquals("1.0 2.0; 3.0 4.0", array1.toInputString());
    }

    @Test
    public void setAndGetShownWork() {
        BooleanMask shown = array1.getShown();
        assertEquals(true, shown.getMask()[0][0]);
        assertEquals(true, shown.getMask()[0][1]);
        assertEquals(true, shown.getMask()[1][0]);
        assertEquals(true, shown.getMask()[1][1]);

        shown.clearAll();
        assertEquals(false, shown.getMask()[0][0]);
        assertEquals(false, shown.getMask()[0][1]);
        assertEquals(false, shown.getMask()[1][0]);
        assertEquals(false, shown.getMask()[1][1]);

    }

    @Test
    public void setAndGetActivationWork() {
        BooleanMask active = array1.getActivation();
        assertEquals(false, active.getMask()[0][0]);
        assertEquals(false, active.getMask()[0][1]);
        assertEquals(false, active.getMask()[1][0]);
        assertEquals(false, active.getMask()[1][1]);

        active.setAll();
        assertEquals(true, active.getMask()[0][0]);
        assertEquals(true, active.getMask()[0][1]);
        assertEquals(true, active.getMask()[1][0]);
        assertEquals(true, active.getMask()[1][1]);

        active.clearAll();
        assertEquals(false, active.getMask()[0][0]);
        assertEquals(false, active.getMask()[0][1]);
        assertEquals(false, active.getMask()[1][0]);
        assertEquals(false, active.getMask()[1][1]);
    }

    @Test
    public void setUpGridAddsColumnConstraints() {
        array1.draw();
        assertEquals(2, grid1.getColumnConstraints().size());
    }

    @Test
    public void oldConstraintsAreCleared() {
        array1.draw();
        array1.draw();
        assertEquals(2, grid1.getColumnConstraints().size());
        assertEquals(2, grid1.getRowConstraints().size());
    }

    @Test
    public void longStringsLengthenColumns() {
        array1.draw();
        array2.draw();

        double width1 = grid1.getColumnConstraints().get(0).getPrefWidth();
        double width2 = grid2.getColumnConstraints().get(0).getPrefWidth();
        assertEquals(true, width2 > width1);
    }

    @Test
    public void drawPopulatesGrid() {
        array1.draw();
        assertEquals(4, grid1.getChildren().size());
        assertEquals(StackPane.class, grid1.getChildren().get(0).getClass());
    }

}
