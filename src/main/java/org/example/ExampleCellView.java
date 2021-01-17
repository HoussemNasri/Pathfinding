package org.example;

import javafx.scene.control.Control;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ExampleCellView extends Control {
    public ExampleCellView() {
        setPrefSize(100, 50);
        HBox layout = new HBox();
        layout.setStyle("-fx-background-color: red");

        Text text = new Text("Text");
        layout.getChildren().add(text);

        getChildren().setAll(layout);
    }
}
