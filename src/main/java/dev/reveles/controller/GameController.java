package dev.reveles.controller;

import dev.reveles.model.Ball;
import dev.reveles.model.Client;
import dev.reveles.model.Dice;
import java.util.ArrayList;

public class GameController
{
    private ArrayList<Client> clients;
    private int turn;
    private int[][] rolled;
    private int[][] moved;
    private int[] throughput;
    private int[] numberInSystem;
    private ArrayList<Integer> timeInSystem;

    public GameController()
    {
        reset();
    }

    public void rollDice()
    {
        for(Client client : clients)
        {
            for(Dice dice : client.getDices())
            {
                dice.roll();
            }

            if(turn < 20)
            {
                rolled[client.getId() - 1][turn + 1] = client.getDicesValue();
            }
        }
    }

    public void nextTurn()
    {
        if(turn < 20) {turn ++;}
    }

    public void recordTurn(int[] movedThisTurn)
    {
        for(int i = 0; i < 10; i ++)
        {
            moved[i][turn] = movedThisTurn[i];
        }

        throughput[turn] = clients.get(9).getLineLenght();

        int total = 0;
        for(Client client : clients)
        {
            total += client.getLineLenght();
        }

        numberInSystem[turn] = total;
    }

    public void updateTimeInSystem(Ball ball)
    {
        if(ball.getValue() == 1)
        {
            timeInSystem.add(turn - ball.getSpawnTurn());
        }
    }

    public void reset()
    {
        clients = new ArrayList<Client>();
        turn = 0;

        for(int i = 1; i <= 10; i ++)
        {
            clients.add(new Client(i));
        }

        rolled = new int[10][21];
        moved = new int[10][21];
        throughput = new int[21];
        numberInSystem = new int[21];
        timeInSystem = new ArrayList<Integer>();
        numberInSystem[0] = 36;
    }

    public int getRolled(int player, int turn) {return rolled[player - 1][turn];}
    public int getMoved(int player, int turn) {return moved[player - 1][turn];}

    public double getAllRolled(int turn)
    {
        int total = 0;
        for(int i = 0; i < 10; i ++)
        {
            total += rolled[i][turn];
        }
        return total / 10.0;
    }

    public double getAllMoved(int turn)
    {
        int total = 0;
        for(int i = 0; i < 10; i ++)
        {
            total += moved[i][turn];
        }
        return total / 10.0;
    }

    public int getThroughput(int turn) {return throughput[turn];}
    public int getNumberInSystem(int turn) {return numberInSystem[turn];}
    public ArrayList<Integer> getTimeInSystem() {return timeInSystem;}
    public ArrayList<Client> getClients() {return clients;}
    public int getTurn() {return turn;}
}