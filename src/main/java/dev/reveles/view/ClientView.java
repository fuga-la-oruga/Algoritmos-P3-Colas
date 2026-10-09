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
    private FlowPane lineArea;
    private HBox infoArea;
    private FlowPane diceArea;

    public ClientView(Client client)
    {
        this.client = client;
        setAlignment(Pos.CENTER);

        setMinWidth(150);
        setMaxWidth(150);
        setPrefWidth(150);

        createLineArea();
        createInfoArea();
        createDiceArea();

        getChildren().addAll(lineArea, infoArea, diceArea);
    }

    private void createLineArea()
    {
        lineArea = new FlowPane();
        lineArea.setAlignment(Pos.TOP_LEFT);
        lineArea.setPadding(new Insets(8));
        lineArea.setHgap(2);
        lineArea.setVgap(2);
        lineArea.setPrefHeight(370);
        lineArea.setScaleY(-1);

        for (int i = client.getLineLenght() - 1; i >= 0; i --)
        {
            lineArea.getChildren().add(new BallView(client.getFromLine(i)));
        }

        lineArea.setBorder
        (
            new Border
            (
                new BorderStroke
                (
                    Color.rgb(192, 192, 192),
                    BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY,
                    new BorderWidths(3)
                )
            )
        );
    }

    private void createInfoArea()
    {
        Label person = new Label(getPersonEmoji(client.getId()));
        person.setStyle("-fx-font-size: 42px;");
        Label number = new Label(String.valueOf(client.getId()));
        number.setStyle("-fx-font-size: 20px; -fx-text-fill: #C0C0C0; -fx-font-weight: bold;");

        infoArea = new HBox();
        infoArea.setAlignment(Pos.CENTER);
        infoArea.setSpacing(8);
        infoArea.getChildren().addAll(person, number);

        infoArea.setBorder
        (
            new Border
            (
                new BorderStroke
                (
                    Color.rgb(192, 192, 192),
                    BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY,
                    new BorderWidths(3)
                )
            )
        );
    }

    private void createDiceArea()
    {
        diceArea = new FlowPane();
        diceArea.setAlignment(Pos.TOP_LEFT);
        diceArea.setPadding(new Insets(8));
        diceArea.setHgap(3);
        diceArea.setVgap(3);
        diceArea.setPrefHeight(185);

        for(Dice dice : client.getDices())
        {
            DiceView diceView = new DiceView(dice.getValue());
            diceArea.getChildren().add(diceView);
        }

        diceArea.setBorder
        (
            new Border
            (
                new BorderStroke
                (
                    Color.rgb(192, 192, 192),
                    BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY,
                    new BorderWidths(3)
                )
            )
        );
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

    public Client getClient() {return client;}

    public FlowPane getDiceArea() {return diceArea;}

    public void setDiceSelected(boolean selected)
    {
        if(selected)
        {
            diceArea.setBorder
            (
                new Border
                (
                    new BorderStroke
                    (
                        Color.rgb(63, 63, 63),
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        new BorderWidths(3)
                    )
                )
            );
        }
        else
        {
            diceArea.setBorder
            (
                new Border
                (
                    new BorderStroke
                    (
                        Color.rgb(192, 192, 192),
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        new BorderWidths(3)
                    )
                )
            );
        }
    }
}