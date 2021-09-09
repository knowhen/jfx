module org.when.jfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.when.jfx to javafx.fxml;
    exports org.when.jfx;
}