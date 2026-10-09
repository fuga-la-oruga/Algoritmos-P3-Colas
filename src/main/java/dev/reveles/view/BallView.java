package dev.reveles.view;

import dev.reveles.model.Ball;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BallView extends Circle
{
    public BallView(Ball ball)
    {
        super(8);

        if(ball.getValue() == 1)
        {
            setFill(Color.rgb(0, 148, 255));
        }
        else
        {
            setFill(Color.rgb(128, 128, 128));
        }
    }
}