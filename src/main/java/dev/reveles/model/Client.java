package dev.reveles.model;

import java.util.ArrayList;

public class Client
{
    private int id;
    private int balls;
    private ArrayList<Dice> dices;

    public Client(int id)
    {
        this.id = id;
        balls = 4;
        dices = new ArrayList<Dice>();
        dices.add(new Dice());
    }

    public int getId()
    {
        return id;
    }

    public int getBalls()
    {
        return balls;
    }

    public ArrayList<Dice> getDices()
    {
        return dices;
    }

    public int getDicesValue()
    {
        if(!hasDices())
        {
            return 0;
        }
        int total = 0;
        for(Dice dice : dices)
        {
            total += dice.getValue();
        }
        return total;
    }

    public boolean hasDices()
    {
        if(dices.size() == 0)
        {
            return false;
        }
        return true;
    }
}