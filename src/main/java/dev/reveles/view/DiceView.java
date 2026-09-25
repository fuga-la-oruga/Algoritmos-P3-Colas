package dev.reveles.view;

import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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
        dots.setPrefSize(32, 32);
        dots.setMinSize(32, 32);
        dots.setMaxSize(32, 32);
        dots.setAlignment(Pos.CENTER);

        for (int i = 0; i < 3; i ++)
        {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100/3);
            dots.getColumnConstraints().add(column);
        }

        for (int i = 0; i < 3; i ++)
        {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100/3);

            dots.getRowConstraints().add(row);
        }

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
                break;
            }

            case 2:
            {
                addDot(0, 0);
                addDot(2, 2);
                break;
            }

            case 3:
            {
                addDot(0, 0);
                addDot(1, 1);
                addDot(2, 2);
                break;
            }

            case 4:
            {
                addDot(0, 0);
                addDot(0, 2);
                addDot(2, 0);
                addDot(2, 2);
                break;
            }

            case 5:
            {
                addDot(0, 0);
                addDot(0, 2);
                addDot(1, 1);
                addDot(2, 0);
                addDot(2, 2);
                break;
            }

            case 6:
            {
                addDot(0, 0);
                addDot(1, 0);
                addDot(2, 0);
                addDot(0, 2);
                addDot(1, 2);
                addDot(2, 2);
                break;
            }
        }
    }

    private void addDot(int row, int col)
    {
        Circle dot = new Circle(3);
        dot.setFill(Color.WHITE);

        StackPane cell = new StackPane(dot);
        cell.setAlignment(Pos.CENTER);

        dots.add(cell, col, row);
    }

    public int getValue()
    {
        return value;
    }
}