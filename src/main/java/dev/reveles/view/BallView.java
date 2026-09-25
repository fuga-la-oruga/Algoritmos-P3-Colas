package dev.reveles.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BallView extends Circle
{
    public BallView(boolean blue)
    {
        super(8);

        if (blue)
        {
            setFill(Color.rgb(0, 148, 255));
        }
        else
        {
            setFill(Color.rgb(128, 128, 128));
        }
    }
}