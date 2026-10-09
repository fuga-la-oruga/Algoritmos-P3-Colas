package dev.reveles.view;

import dev.reveles.controller.GameController;
import dev.reveles.model.Ball;
import dev.reveles.model.Client;
import dev.reveles.model.Dice;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class GameView extends BorderPane
{
    private GameController controller;
    private GridPane clientGrid;
    private Button actionButton;
    private Label turnLabel;
    private boolean rolled;
    private Client selectedClient;

    public GameView(GameController controller)
    {
        this.controller = controller;
        rolled = false;
        selectedClient = null;
        createClients();
        createControls();
        updateClients();
    }

    private void createClients()
    {
        clientGrid = new GridPane();
        clientGrid.setAlignment(Pos.CENTER);
        clientGrid.setHgap(0);
        clientGrid.setVgap(0);

        for (Client client : controller.getClients())
        {
            ClientView clientView = new ClientView(client);
            clientGrid.add(clientView, client.getId() - 1, 0);
        }

        ScrollPane scrollPane = new ScrollPane(clientGrid);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setCenter(scrollPane);
    }

    private void createControls()
    {
        actionButton = new Button("Roll");
        actionButton.setPrefSize(100, 60);
        actionButton.setStyle("-fx-font-size: 20px;");
        actionButton.setOnAction(event -> handleAction());

        turnLabel = new Label("Turn: 0");
        turnLabel.setStyle("-fx-font-size: 32px;");

        HBox leftControls = new HBox(15);
        leftControls.setAlignment(Pos.CENTER_LEFT);
        leftControls.getChildren().addAll(actionButton, turnLabel);

        Button activityButton = new Button("Activity");
        Button throughputButton = new Button("Throughput");
        Button systemButton = new Button("Number in System");
        Button timeButton = new Button("Time in System");

        activityButton.setOnAction(event ->
                new ChartWindow(controller, "Activity").open()
        );

        throughputButton.setOnAction(event ->
                new ChartWindow(controller, "Throughput").open()
        );

        systemButton.setOnAction(event ->
                new ChartWindow(controller, "Number in System").open()
        );

        timeButton.setOnAction(event ->
                new ChartWindow(controller, "Time in System").open()
        );

        HBox chartControls = new HBox(10);
        chartControls.setAlignment(Pos.CENTER);
        chartControls.getChildren().addAll(
                activityButton,
                throughputButton,
                systemButton,
                timeButton
        );

        Button resetButton = new Button("Reset");
        resetButton.setPrefSize(100, 60);
        resetButton.setStyle("-fx-font-size: 16px;");
        resetButton.setOnAction(event -> reset());

        HBox rightControls = new HBox();
        rightControls.setAlignment(Pos.CENTER_RIGHT);
        rightControls.getChildren().add(resetButton);

        HBox controls = new HBox(15);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(15));

        HBox.setHgrow(leftControls, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(chartControls, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(rightControls, javafx.scene.layout.Priority.ALWAYS);

        controls.getChildren().addAll(
                leftControls,
                chartControls,
                rightControls
        );

        setBottom(controls);
    }

    private void handleAction()
    {
        if (!rolled)
        {
            controller.rollDice();
            updateClients();
            actionButton.setText("Move");
            rolled = true;
        }

        else
        {
            controller.nextTurn();
            updateTurn();
            actionButton.setText("Roll");
            rolled = false;

            if(controller.getTurn() >= 20)
            {
                actionButton.setDisable(true);
            }

            ArrayList<Client> clients = controller.getClients();
            int[] movedThisTurn = new int[10];

            for(int i = 9; i >= 0; i --)
            {
                if(i == 0)
                {
                    int amountToAdd = clients.get(0).getDicesValue();
                    movedThisTurn[0] = amountToAdd;

                    while(amountToAdd > 0)
                    {
                        clients.get(0).addToLine(new Ball(1, controller.getTurn()));
                        amountToAdd --;
                    }

                    continue;
                }

                int amountToPass = clients.get(i - 1).getLineLenght() >= clients.get(i).getDicesValue() ?
                    clients.get(i).getDicesValue() :
                    clients.get(i - 1).getLineLenght();

                movedThisTurn[i - 1] = amountToPass;

                while(amountToPass > 0)
                {
                    Ball ball = clients.get(i - 1).removeFromLine();

                    if(ball != null)
                    {
                        clients.get(i).addToLine(ball);

                        if(i == 9)
                        {
                            controller.updateTimeInSystem(ball);
                        }
                    }

                    amountToPass --;
                }
            }

            controller.recordTurn(movedThisTurn);
        }
        updateClients();
    }


    private void updateClients()
    {
        clientGrid.getChildren().clear();

        for (Client client : controller.getClients())
        {
            ClientView clientView = new ClientView(client);

            clientView.setDiceSelected
            (
                selectedClient != null &&
                selectedClient.getId() == client.getId()
            );

            clientView.getDiceArea().setOnMouseClicked(event ->
            {
                handleDiceClick(client);
            });

            clientGrid.add(clientView, client.getId() - 1, 0);
        }
    }

    private void updateTurn() {turnLabel.setText("Turn: " + controller.getTurn());}

    private void reset()
    {
        selectedClient = null;
        controller.reset();
        rolled = false;
        actionButton.setText("Roll");
        actionButton.setDisable(false);
        updateTurn();
        updateClients();
    }

    private void handleDiceClick(Client client)
    {
        if (rolled || controller.getTurn() == 20) {return;}

        if (selectedClient == null)
        {
            if (!client.hasDices()) {return;}

            selectedClient = client;
            actionButton.setDisable(true);
            updateClients();
            return;
        }

        if (selectedClient.getId() == client.getId())
        {
            selectedClient = null;
            actionButton.setDisable(false);
            updateClients();
            return;
        }

        client.addDice(selectedClient.removeDice());
        selectedClient = null;
        actionButton.setDisable(false);
        updateClients();
    }
}