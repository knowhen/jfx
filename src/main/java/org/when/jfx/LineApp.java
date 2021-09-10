package org.when.jfx;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * @author: when
 * @create: 2021-09-09  20:51
 **/
public class LineApp extends BaseApp {

    private static final int LINE_NUMBERS = 10;
    private static final double WIDTH = 600;
    private static final double HEIGHT = 400;

    private Line line;

    @Override
    protected Parent createContent() {
        Pane root = new Pane();
        root.setMinSize(WIDTH, HEIGHT);
        root.setMaxSize(WIDTH, HEIGHT);

        root.getChildren().add(createRandomLine());
        return root;
    }

    private Line createRandomLine() {
        double startX = Math.random() * WIDTH;
        double startY = Math.random() * HEIGHT;
        double endX = Math.random() * WIDTH;
        double endY = Math.random() * HEIGHT;

        // create line
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.RED);
        line.setStrokeWidth(2);
        return line;
    }

    public static void main(String[] args) {
        launch();
    }
}
