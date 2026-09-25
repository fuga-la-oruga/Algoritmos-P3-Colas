package dev.reveles.view;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class DiceView extends StackPane
{
    private GridPane dots;
    private int value;

    public DiceView(int value)
    {
        this.value = value;

        Rectangle background = new Rectangle(32, 32);
        background.setArcWidth(10);
        background.setArcHeight(10);
        background.setFill(Color.rgb(255, 127, 127));

        dots = new GridPane();
        dots.setHgap(5);
        dots.setVgap(5);
        dots.setAlignment(Pos.CENTER);

        getChildren().addAll(background, dots);
        setValue(value);
    }

    public void setValue(int value)
    {
        this.value = value;
        dots.getChildren().clear();

        switch (value)
        {
            case 1:
            {
                addDot(1, 1);
            }
            case 2:
            {
                addDot(0, 0);
                addDot(2, 2);
            }
            case 3:
            {
                addDot(0, 0);
                addDot(1, 1);
                addDot(2, 2);
            }
            case 4:
            {
                addDot(0, 0);
                addDot(0, 2);
                addDot(2, 0);
                addDot(2, 2);
            }
            case 5:
            {
                addDot(0, 0);
                addDot(0, 2);
                addDot(1, 1);
                addDot(2, 0);
                addDot(2, 2);
            }
            case 6:
            {
                addDot(0, 0);
                addDot(1, 0);
                addDot(2, 0);
                addDot(0, 2);
                addDot(1, 2);
                addDot(2, 2);
            }
        }
    }

    private void addDot(int row, int col)
    {
        Circle dot = new Circle(4);
        dot.setFill(Color.rgb(255, 255, 255));
        dots.add(dot, col, row);
    }

    public int getValue()
    {
        return value;
    }
}