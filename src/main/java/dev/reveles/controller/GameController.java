package dev.reveles.controller;

import dev.reveles.model.Client;
import java.util.ArrayList;

public class GameController
{
    private ArrayList<Client> clients;
    private int turn;

    public GameController()
    {
        clients = new ArrayList<>();
        turn = 0;

        for (int i = 1; i <= 10; i ++)
        {
            clients.add(new Client(i));
        }
    }

    public void rollDice()
    {
        for (Client client : clients)
        {
            client.getDice().roll();
        }
    }

    public void nextTurn()
    {
        if (turn < 20)
        {
            turn ++;
        }
    }

    public void reset()
    {
        clients.clear();
        turn = 0;

        for (int i = 1; i <= 10; i ++)
        {
            clients.add(new Client(i));
        }
    }

    public ArrayList<Client> getClients()
    {
        return clients;
    }

    public int getTurn()
    {
        return turn;
    }
}