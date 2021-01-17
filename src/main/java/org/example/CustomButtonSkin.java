package org.example;

import javafx.scene.control.Button;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.text.Text;

public class CustomButtonSkin extends ButtonSkin {
    public CustomButtonSkin(Button control) {
        super(control);
        getNode().setStyle("-fx-background-color: red");
        getChildren().setAll(new Text("Ni"));
    }
}
