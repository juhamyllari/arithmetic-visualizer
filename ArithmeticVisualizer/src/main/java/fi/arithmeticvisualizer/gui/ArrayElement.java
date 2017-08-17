package fi.arithmeticvisualizer.gui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ArrayElement {

    private final Color ACTIVECOLOUR = Color.RED;

    private int elementId;
    private DoubleProperty value;
    private StringProperty valueAsString;
    private BooleanProperty active;
    private BooleanProperty show;
    private Text elementText;
    private GridPane grid;

    public ArrayElement(int elementId, Double value, GridPane grid, boolean show) {
        this.elementId = elementId;
        this.value = new SimpleDoubleProperty(value);
        this.grid = grid;
        this.active = new SimpleBooleanProperty(false);
        this.show = new SimpleBooleanProperty(show);
        this.elementText = new Text();
        this.elementText.setText(Double.toString(this.value.getValue()));
    }

    public Text getInitialText() {
        return elementText;
    }

    public Text getTextFromGrid(int elementID) {
        return (Text) grid.getChildren().get(elementID);
    }

    public double getValue() {
        return value.getValue();
    }

    public void setValue(double value) {
        this.value.set(value);
        getTextFromGrid(elementId).setText(Double.toString(value));
    }

    public void setActive(boolean active) {
        this.active.set(active);
        setColour();
    }

    private void setColour() {
        if (active.get()) {
            getTextFromGrid(elementId).setFill(ACTIVECOLOUR);
        } else {
            getTextFromGrid(elementId).setFill(ACTIVECOLOUR);
        }
    }

    void setShow(boolean show) {
        this.show.set(show);
    }

}
