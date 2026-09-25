package dev.reveles.model;

public class Ball
{
    private boolean blue;

    public Ball()
    {
        blue = false;
    }

    public Ball(boolean blue)
    {
        this.blue = blue;
    }

    public boolean isBlue()
    {
        return blue;
    }
}