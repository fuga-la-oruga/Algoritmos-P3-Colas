package dev.reveles.model;

import java.util.ArrayList;

public class Client
{
    private int id;
    private ArrayList<Dice> dices;
    private ArrayList<Ball> line;

    public Client(int id)
    {
        this.id = id;
        dices = new ArrayList<Dice>();
        dices.add(new Dice());
        line = new ArrayList<Ball>();

        if(id != 10)
        {
            for(int i = 0; i < 4; i ++)
            {
                line.add(new Ball(0, -1));
            }
        }
    }

    public int getId() {return id;}
    public ArrayList<Dice> getDices() {return dices;}
    public ArrayList<Ball> getLine() {return line;}

    public int getDicesValue()
    {
        int total = 0;
        for(Dice dice : dices)
        {
            total += dice.getValue();
        }
        return total;
    }

    public void addDice(Dice dice)
    {
        dices.add(dice);
    }

    public Dice removeDice()
    {
        if(dices.size() == 0) {return null;}

        Dice toReturn = dices.get(dices.size() - 1);
        dices.remove(dices.size() - 1);
        return toReturn;
    }

    public boolean hasDices()
    {
        return dices.size() > 0;
    }

    public int getLineLenght()
    {
        return line.size();
    }

    public void addToLine(Ball ball)
    {
        line.add(ball);
    }

    public Ball removeFromLine()
    {
        if(line.size() == 0) {return null;}

        Ball toReturn = line.get(0);
        line.remove(0);
        return toReturn;
    }

    public Ball getFromLine(int index)
    {
        return line.get(index);
    }
}