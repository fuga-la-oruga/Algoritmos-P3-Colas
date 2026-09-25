package dev.reveles.view;

import dev.reveles.model.Client;
import dev.reveles.model.Dice;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ClientView extends VBox
{
    private Client client;
    private FlowPane ballArea;
    private HBox infoArea;
    private FlowPane diceArea;

    public ClientView(Client client)
    {
        this.client = client;

        setAlignment(Pos.CENTER);

        setMinWidth(150);
        setMaxWidth(150);
        setPrefWidth(150);

        setMinHeight(600);
        setMaxHeight(600);
        setPrefHeight(600);

        createBallArea();
        createInfoArea();
        createDiceArea();

        getChildren().addAll(ballArea, infoArea, diceArea);
    }

    private void createBallArea()
    {
        ballArea = new FlowPane();
        ballArea.setAlignment(Pos.BOTTOM_CENTER);
        ballArea.setPadding(new Insets(8));
        ballArea.setHgap(3);
        ballArea.setVgap(3);
        ballArea.setPrefHeight(300);

        for (int i = 0; i < client.getBalls(); i ++)
        {
            ballArea.getChildren().add(new BallView(false));
        }

        Border border = new Border
        (
            new BorderStroke
            (
                Color.rgb(192, 192, 192),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(3)
            )
        );

        ballArea.setBorder(border);
    }

    private void createInfoArea()
    {
        infoArea = new HBox();
        infoArea.setAlignment(Pos.CENTER);
        infoArea.setSpacing(8);

        Label person = new Label(getPersonEmoji(client.getId()));
        person.setStyle("-fx-font-size: 42px;");

        Label number = new Label(String.valueOf(client.getId()));
        number.setStyle("-fx-font-size: 20px; -fx-text-fill: #C0C0C0;");

        infoArea.getChildren().addAll(person, number);

        Border border = new Border
        (
            new BorderStroke
            (
                Color.rgb(192, 192, 192),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(3)
            )
        );

        infoArea.setBorder(border);
    }

    private void createDiceArea()
    {
        diceArea = new FlowPane();
        diceArea.setAlignment(Pos.TOP_CENTER);
        diceArea.setPadding(new Insets(8));
        diceArea.setHgap(3);
        diceArea.setVgap(3);
        diceArea.setPrefHeight(250);

        for(Dice dice : client.getDices())
        {
            DiceView diceView = new DiceView(dice.getValue());
            diceArea.getChildren().add(diceView);
        }

        Border border = new Border
        (
            new BorderStroke
            (
                Color.rgb(192, 192, 192),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(3)
            )
        );

        diceArea.setBorder(border);
    }

    private String getPersonEmoji(int id)
    {
        String[] people =
        {
            "👨‍🦰",
            "👨‍🦱",
            "👨‍🦳",
            "👩‍🦰",
            "👨‍🦰",
            "👨‍🦱",
            "👨‍🦳",
            "👩‍🦰",
            "👨‍🦰",
            "👨‍🦱"
        };
        return people[id - 1];
    }

    public void updateDice()
    {
        diceArea.getChildren().clear();

        for(Dice dice : client.getDices())
        {
            DiceView diceView = new DiceView(dice.getValue());
            diceArea.getChildren().add(diceView);
        }
    }

    public Client getClient()
    {
        return client;
    }
}