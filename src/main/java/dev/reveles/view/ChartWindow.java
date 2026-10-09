package dev.reveles.view;

import dev.reveles.controller.GameController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChartWindow
{
    private GameController controller;
    private String chartType;
    private BarChart<String, Number> chart;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private ChoiceBox<String> playerChoice;
    private ChoiceBox<String> actionChoice;

    public ChartWindow(GameController controller, String chartType)
    {
        this.controller = controller;
        this.chartType = chartType;
    }

    public void open()
    {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(chartType);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        if(chartType.equals("Activity"))
        {
            playerChoice = new ChoiceBox<>();
            playerChoice.getItems().add("All");

            for(int i = 1; i <= 10; i ++)
            {
                playerChoice.getItems().add("Player " + i);
            }

            playerChoice.setValue("Player 1");

            actionChoice = new ChoiceBox<>();
            actionChoice.getItems().addAll("Moved", "Rolled");
            actionChoice.setValue("Moved");

            HBox controls = new HBox(15);
            controls.setPadding(new Insets(10));
            controls.getChildren().addAll(playerChoice, actionChoice);
            root.setTop(controls);

            playerChoice.setOnAction(event -> updateChart());
            actionChoice.setOnAction(event -> updateChart());
        }

        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();

        xAxis.setLabel("Turn");

        if(chartType.equals("Activity"))
        {
            yAxis.setLabel("Number");
        }
        else if(chartType.equals("Throughput"))
        {
            yAxis.setLabel("Throughput");
        }
        else if(chartType.equals("Number in System"))
        {
            yAxis.setLabel("Number in System");
        }
        else
        {
            xAxis.setLabel("Order of Arrival");
            yAxis.setLabel("Time in System");
        }

        chart = new BarChart<>(xAxis, yAxis);
        chart.setAnimated(false);

        root.setCenter(chart);
        updateChart();

        stage.setScene(new Scene(root, 900, 600));
        stage.showAndWait();
    }

    private void updateChart()
    {
        chart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        ArrayList<String> categories = new ArrayList<>();

        if(chartType.equals("Activity"))
        {
            String player = playerChoice.getValue();
            String activity = actionChoice.getValue();
            series.setName(player + " - " + activity);

            for(int turn = 1; turn <= 20; turn ++)
            {
                categories.add(String.valueOf(turn));
                double value;

                if(player.equals("All"))
                {
                    if(activity.equals("Moved"))
                    {
                        value = controller.getAllMoved(turn);
                    }
                    else
                    {
                        value = controller.getAllRolled(turn);
                    }
                }
                else
                {
                    int playerNumber = Integer.parseInt(player.substring(7));

                    if(activity.equals("Moved"))
                    {
                        value = controller.getMoved(playerNumber, turn);
                    }
                    else
                    {
                        value = controller.getRolled(playerNumber, turn);
                    }
                }

                series.getData().add(new XYChart.Data<>(String.valueOf(turn), value));
            }
        }
        else if(chartType.equals("Throughput"))
        {
            series.setName("Player 10");

            for(int turn = 1; turn <= 20; turn ++)
            {
                categories.add(String.valueOf(turn));
                series.getData().add(new XYChart.Data<>
                (
                    String.valueOf(turn),
                    controller.getThroughput(turn)
                ));
            }
        }
        else if(chartType.equals("Number in System"))
        {
            series.setName("Total Balls");

            for(int turn = 0; turn <= 20; turn ++)
            {
                categories.add(String.valueOf(turn));

                series.getData().add(new XYChart.Data<>
                (
                    String.valueOf(turn),
                    controller.getNumberInSystem(turn)
                ));
            }
        }
        else
        {
            series.setName("Blue Balls");
            ArrayList<Integer> times = controller.getTimeInSystem();

            for(int i = 0; i < times.size(); i ++)
            {
                int order = i + 1;
                categories.add(String.valueOf(order));
                series.getData().add(new XYChart.Data<>(String.valueOf(order), times.get(i)));
            }
        }

        xAxis.setCategories(FXCollections.observableArrayList(categories));
        chart.getData().add(series);
    }
}