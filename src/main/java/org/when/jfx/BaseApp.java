package org.when.jfx;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author: when
 * @create: 2021-09-08  18:18
 **/
public abstract class BaseApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    protected abstract Parent createContent();
}
