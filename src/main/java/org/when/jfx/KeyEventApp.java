package org.when.jfx;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.util.function.BiFunction;

/**
 * @author: when
 * @create: 2021-09-08  18:22
 **/
public class KeyEventApp extends BaseApp {

    // console for logging key events
    private ListView<String> console;

    @Override
    protected Parent createContent() {
        console = createLoggingConsole();

        // create text box for typing
        TextField textBox = createTextBox();

        return createVBox(textBox, console);
    }

    private ListView<String> createLoggingConsole() {
        ListView<String> console = new ListView<>(FXCollections.observableArrayList());
        console.getItems().addListener(createListChangeListener());
        console.setPrefHeight(150);
        console.setMaxHeight(ListView.USE_PREF_SIZE);
        return console;
    }

    private ListChangeListener<? super String> createListChangeListener() {
        return change -> {
            while (change.next()) {
                ObservableList<? extends String> changeList = change.getList();
                if (changeList.size() > 20) {
                    changeList.remove(0);
                }
            }
        };
    }

    private TextField createTextBox() {
        TextField textBox = new TextField();
        textBox.setPromptText("Write here");
        textBox.setStyle("-fx-font-size: 26;");
        textBox.setOnKeyPressed(displayInConsole("Key pressed: ", this::simpleConcat));
        textBox.setOnKeyReleased(displayInConsole("Key pressed: ", this::simpleConcat));
        textBox.setOnKeyTyped(displayInConsole("Key typed: ", this::complexConcat));

        return textBox;
    }

    private EventHandler<? super KeyEvent> displayInConsole(String prefix, BiFunction<String, KeyEvent,
            String> biFunction) {
        return event -> {
            String text = biFunction.apply(prefix, event);
            console.getItems().add(text);
        };
    }

    private String simpleConcat(String prefix, KeyEvent e) {
        return prefix + e.getText();
    }

    private String complexConcat(String prefix, KeyEvent e) {
        String text = prefix + e.getCharacter();
        if (e.isAltDown()) {
            text += " , alt down";
        }
        if (e.isControlDown()) {
            text += " , ctrl down";
        }
        if (e.isMetaDown()) {
            text += " , meta down";
        }
        if (e.isShiftDown()) {
            text += " , shift down";
        }
        return text;
    }

    private VBox createVBox(Node... nodes) {
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(nodes);
        return vBox;
    }


    public static void main(String[] args) {
        launch();
    }
}
