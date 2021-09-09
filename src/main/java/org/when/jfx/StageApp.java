package org.when.jfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author: when
 * @create: 2021-09-08  12:21
 **/
public class StageApp extends BaseApp {

    public static void main(String[] args) {
        launch();
    }

    @Override
    protected Parent createContent() {
        // create a button for initializing the stage
        Button button = new Button("Create a Stage");
        button.setStyle("-fx-font-size: 26;");
        button.setDefaultButton(true);
        button.setOnAction(e -> {

            final Stage stage = new Stage();

            // create root node of scene
            Group root = new Group();

            // create scene with width, height and color
            Scene scene = new Scene(root, 200, 200, Color.YELLOW);

            // set scene to stage
            stage.setScene(scene);
            // set title to stage
            stage.setTitle("Stage App");
            // center stage on screen
            stage.centerOnScreen();
            // show stage
            stage.show();

            // add some node to scene
            Text text = new Text(20, 110, "JavaFX");
            text.setFill(Color.GREEN);
            text.setEffect(new Lighting());
            text.setFont(Font.font(Font.getDefault().getFamily(), 50));

            // add text to root group
            root.getChildren().add(text);

        });
        return button;
    }
}
