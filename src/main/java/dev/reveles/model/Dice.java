package dev.reveles.model;

import java.util.Random;

public class Dice
{
    private int value;
    private Random random;

    public Dice()
    {
        random = new Random();
        value = 1;
    }

    public int roll() {return value = random.nextInt(6) + 1;}
    public int getValue() {return value;}
}