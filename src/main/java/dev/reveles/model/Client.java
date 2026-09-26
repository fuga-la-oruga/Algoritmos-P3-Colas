package dev.reveles.model;

import java.util.ArrayList;

public class Client
{
    private int id;
    private ArrayList<Dice> dices;
    private ArrayList<Integer> line;

    public Client(int id)
    {
        this.id = id;
        dices = new ArrayList<Dice>();
        dices.add(new Dice());
        line = new ArrayList<Integer>();
        if(id != 10)
        {
            line.add(0); line.add(0);
            line.add(0); line.add(0);
        }
    }

    public int getId() {return id;}
    public ArrayList<Dice> getDices() {return dices;}

    public int getDicesValue()
    {
        if(!hasDices()) {return 0;}
        int total = 0;
        for(Dice dice : dices) {total += dice.getValue();}
        return total;
    }

    public void addDice(Dice dice)
    {
        dices.add(dice);
    }

    public Dice removeDice()
    {
        if(!hasDices()) {return null;}
        Dice toReturn = dices.get(dices.size() - 1);
        dices.remove(dices.size() - 1);
        return toReturn;
    }

    public boolean hasDices()
    {
        if(dices.size() == 0) {return false;}
        return true;
    }

    public int getLineLenght() {return line.size();}

    public void addToLine(int element)
    {
        if(element != 1 && element != 0) {return;}
        line.add(element);
    }
    public int removeFromLine()
    {
        if(line.size() == 0)
        {
            return -1;
        }
        else
        {
            int toReturn = line.get(0);
            line.remove(0);
            return toReturn;
        }
    }
    public int getFromLine(int index) {return line.get(index);}
}