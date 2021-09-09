package org.when.jfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author: when
 * @create: 2021-09-08  12:40
 **/
public class AdvancedStageApp extends BaseApp {

    // variables for initial position of the stage at the beginning of drag
    private double initX;
    private double initY;

    @Override
    protected Parent createContent() {
        // create a button for initializing our new stage
        Button button = new Button("Create a Stage");
        button.setStyle("-fx-font-size: 24;");
        button.setDefaultButton(true);
        button.setOnAction(e -> {
            // INITIALISATION OF THE STAGE/SCENE

            // create stage which has set stage style transparent
            final Stage stage = new Stage(StageStyle.TRANSPARENT);

            // create root node of scene
            Group root = new Group();

            // create scene with set width, height and color
            Scene scene = new Scene(root, 200, 200, Color.TRANSPARENT);

            // set scene to stage
            stage.setScene(scene);

            // center stage on screen
            stage.centerOnScreen();

            // show the stage
            stage.show();

            // CREATION OF THE DRAGGER (CIRCLE)

            // create dragger with desired size
            Circle dragger = new Circle(100, 100, 100);

            // fill the dragger with some nice radial background
            dragger.setFill(new RadialGradient(-0.3, 135, 0.5, 0.5, 1, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, Color.DARKGRAY),
                    new Stop(1, Color.BLACK)));

            // when mouse button is pressed, save the initial position of screen
            root.setOnMouseClicked(clicked -> {
                initX = clicked.getSceneX() - stage.getX();
                initY = clicked.getSceneY() - stage.getY();
            });

            // when screen is dragged, translate it accordingly
            root.setOnMouseDragged(dragged -> {
                stage.setX(dragged.getSceneX() - initX);
                stage.setY(dragged.getSceneY() - initY);
            });

            // CREATE MIN AND CLOSE BUTTONS

            // create button for closing application
            Button close = new Button("Close");
            close.setOnAction(closed -> stage.close());

            // create button for minimizing application
            Button min = new Button("Minimize");
            min.setOnAction(minimized -> stage.setIconified(true));


            // CREATE SIMPLE TEXT NODE
            Text text = new Text("JavaFX"); // 20, 110
            text.setFill(Color.WHITESMOKE);
            text.setEffect(new Lighting());
            text.setBoundsType(TextBoundsType.VISUAL);
            text.setFont(Font.font(Font.getDefault().getFamily(), 50));

            // USE A LAYOUT VBOX FOR EASIER POSITIONING OF VISUAL NODES ON SCENE
            VBox vBox = new VBox();
            vBox.setSpacing(10);
            vBox.setPadding(new Insets(60, 0, 0, 20));
            vBox.setAlignment(Pos.TOP_CENTER);
            vBox.getChildren().addAll(text, min, close);

            // add all nodes to main root group
            root.getChildren().addAll(dragger, vBox);
        });

        return button;
    }

    public static void main(String[] args) {
        launch();
    }
}
