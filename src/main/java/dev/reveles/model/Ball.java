package dev.reveles.model;

public class Ball
{
    private int value;
    private int spawnTurn;

    public Ball(int value, int spawnTurn)
    {
        this.value = value;
        this.spawnTurn = spawnTurn;
    }

    public int getValue() {return value;}
    public int getSpawnTurn() {return spawnTurn;}
}