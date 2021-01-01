package org.example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;

@FxmlPath("/toolbar_view.fxml")
public class MyToolbarView implements FxmlView<MyToolbarViewModel>, Initializable {
    public static final int HOME_MARKER_TOOL = 1;
    public static final int DESTINATION_MARKER_TOOL = 2;

    @InjectViewModel
    private MyToolbarViewModel viewModel;

    @FXML
    private ToggleGroup gridEditorGroup;

    @FXML
    private RadioButton fenceToolButton;

    @FXML
    private ImageView fenceToolIcon;

    @FXML
    private RadioButton startMarkerButton;

    @FXML
    private ImageView startMarkerIcon;

    @FXML
    private RadioButton goalMarkerButton;

    @FXML
    private ImageView goalMarkerIcon;

    private void initIcons() {
        fenceToolButton.selectedProperty()
                       .addListener(((observable, oldValue, newValue) -> fenceToolIcon.setImage(ToolIconProvider.getFenceToolIcon(newValue))));
        goalMarkerButton.selectedProperty()
                        .addListener(((observable, oldValue, newValue) -> goalMarkerIcon.setImage(ToolIconProvider.getGoalMarkerToolIcon(newValue))));
        startMarkerButton.selectedProperty()
                         .addListener(((observable, oldValue, newValue) -> startMarkerIcon.setImage(ToolIconProvider.getStartMarkerToolIcon(newValue))));
    }

    public MyToolbarViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initIcons();
        gridEditorGroup.selectToggle(fenceToolButton);
        gridEditorGroup.selectedToggleProperty().addListener(((observable, oldValue, newValue) -> {
            viewModel.selectedToolProperty().set(gridEditorGroup.getToggles().indexOf(newValue));
        }));
    }
}
