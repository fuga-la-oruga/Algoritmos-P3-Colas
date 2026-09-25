package dev.reveles.model;

public class Client
{
    private int id;
    private int balls;
    private Dice dice;

    public Client(int id)
    {
        this.id = id;
        balls = 4;
        dice = new Dice();
    }

    public int getId()
    {
        return id;
    }

    public int getBalls()
    {
        return balls;
    }

    public Dice getDice()
    {
        return dice;
    }
}