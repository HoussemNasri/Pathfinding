package org.example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;

@FxmlPath("/toolbar_view.fxml")
public class MyToolbarView implements FxmlView<ToolbarViewModel>, Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
