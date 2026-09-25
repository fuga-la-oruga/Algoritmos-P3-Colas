package dev.reveles.view;

import dev.reveles.controller.GameController;
import dev.reveles.model.Client;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class GameView extends BorderPane
{
    private GameController controller;
    private GridPane clientGrid;
    private Button actionButton;
    private Label turnLabel;

    private boolean rolled;

    public GameView(GameController controller)
    {
        this.controller = controller;
        rolled = false;

        createClients();
        createControls();
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
        actionButton.setPrefWidth(180);
        actionButton.setPrefHeight(65);
        actionButton.setStyle("-fx-font-size: 28px;");
        actionButton.setOnAction(event -> handleAction());

        turnLabel = new Label("Turn: 0");
        turnLabel.setStyle("-fx-font-size: 32px;");

        Button resetButton = new Button("Reset");
        resetButton.setPrefWidth(120);
        resetButton.setPrefHeight(50);
        resetButton.setOnAction(event -> reset());

        HBox controls = new HBox();
        controls.setAlignment(Pos.CENTER_LEFT);
        controls.setSpacing(45);
        controls.setPadding(new Insets(15));
        controls.getChildren().addAll(actionButton, turnLabel, resetButton);

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

            if (controller.getTurn() >= 20)
            {
                actionButton.setDisable(true);
            }
        }
    }

    private void updateClients()
    {
        clientGrid.getChildren().clear();

        for (Client client : controller.getClients())
        {
            ClientView clientView = new ClientView(client);
            clientGrid.add(clientView, client.getId() - 1, 0);
        }
    }

    private void updateTurn()
    {
        turnLabel.setText("Turn: " + controller.getTurn());
    }

    private void reset()
    {
        controller.reset();
        rolled = false;
        actionButton.setText("Roll");
        actionButton.setDisable(false);

        updateTurn();
        updateClients();
    }
}