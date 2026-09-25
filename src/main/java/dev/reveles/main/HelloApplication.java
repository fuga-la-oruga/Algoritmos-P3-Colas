package dev.reveles.main;

import dev.reveles.controller.GameController;
import dev.reveles.view.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage)
    {
        GameController controller = new GameController();
        GameView view = new GameView(controller);
        Scene scene = new Scene(view, 1650, 900);

        stage.setTitle("Resource Reallocation");
        stage.setScene(scene);
        stage.setMinWidth(1200);
        stage.setMinHeight(700);
        stage.show();
    }
}