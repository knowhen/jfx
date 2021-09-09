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

import java.util.function.Consumer;

/**
 * @author: when
 * @create: 2021-09-08  18:22
 **/
public class KeyEventApp extends BaseApp {

    // console for logging key events
    private ListView<String> logConsole;

    @Override
    protected Parent createContent() {
        logConsole = createLoggingConsole();

        // create text box for typing
        TextField textBox = createTextBox();

        return createVBox(textBox, logConsole);
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
        textBox.setOnKeyPressed(displayInConsole("Key pressed: "));
        textBox.setOnKeyReleased(displayInConsole("Key released: "));
        textBox.setOnKeyTyped(displayInConsoleWithLogic("Key typed: "));

        return textBox;
    }

    private EventHandler<? super KeyEvent> displayInConsole(String prefix) {
        Consumer<KeyEvent> simpleDisplay = e -> logConsole.getItems().add(prefix + e.getText());
        return createKeyEventListener(simpleDisplay);
    }

    private EventHandler<? super KeyEvent> displayInConsoleWithLogic(String prefix) {
        Consumer<KeyEvent> displayWithLogic = (e) -> {
            String text = concatKey(prefix, e);
            logConsole.getItems().add(text);
        };
        return createKeyEventListener(displayWithLogic);
    }

    private String concatKey(String prefix, KeyEvent e) {
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

    private EventHandler<? super KeyEvent> createKeyEventListener(Consumer<KeyEvent> consumer) {
        return consumer::accept;
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
