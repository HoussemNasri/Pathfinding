package org.example.mytoolbar;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;

@FxmlPath("/toolbar_view.fxml")
public class MyToolbarView implements FxmlView<MyToolbarViewModel>, Initializable {
    @InjectViewModel
    private MyToolbarViewModel viewModel;

    @FXML
    private ToggleGroup gridEditorGroup;

    @FXML
    private RadioButton wallToolButton;

    @FXML
    private ImageView wallToolIcon;

    @FXML
    private RadioButton startMarkerButton;

    @FXML
    private ImageView startMarkerIcon;

    @FXML
    private RadioButton goalMarkerButton;

    @FXML
    private ImageView goalMarkerIcon;

    @FXML
    private Button stepOutButton;

    @FXML
    private Button playPauseButton;

    @FXML
    private Button stepInButton;

    @FXML
    private Button resetButton;

    @FXML
    private ComboBox<String> algorithmsComboBox;

    private void initIcons() {
        wallToolButton.selectedProperty()
                      .addListener(((observable, oldValue, newValue) -> wallToolIcon.setImage(ToolbarIconProvider.getWallToolIcon(newValue))));
        goalMarkerButton.selectedProperty()
                        .addListener(((observable, oldValue, newValue) -> goalMarkerIcon.setImage(ToolbarIconProvider.getGoalMarkerToolIcon(newValue))));
        startMarkerButton.selectedProperty()
                         .addListener(((observable, oldValue, newValue) -> startMarkerIcon.setImage(ToolbarIconProvider.getStartMarkerToolIcon(newValue))));
    }

    private void setupPlayPauseButton() {
        playPauseButton.setOnMouseClicked(event -> {
            ImageView buttonIcon = new ImageView(ToolbarIconProvider.getPlayPauseButtonIcon(viewModel.isPlaying()));
            playPauseButton.setGraphic(buttonIcon);

            viewModel.playPause();
        });
    }

    public MyToolbarViewModel getViewModel() {
        return viewModel;
    }

    public void initAlgorithms() {
        algorithmsComboBox.getItems().addAll(PathfindingAlgorithmsRegistry
                .getPathfindingAlgorithms()
                .stream()
                .map(PathfindingAlgorithmModel::getFullName)
                .collect(Collectors.toList()));
        algorithmsComboBox.getSelectionModel().selectFirst();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initIcons();
        gridEditorGroup.selectToggle(wallToolButton);
        gridEditorGroup.selectedToggleProperty().addListener(((observable, oldValue, newValue) -> {
            int selectedToolIndex = gridEditorGroup.getToggles().indexOf(newValue);
            viewModel.selectedGridToolProperty().set(GridTool.values()[selectedToolIndex]);
        }));

        stepInButton.setOnMouseClicked(e -> viewModel.stepIn());
        stepOutButton.setOnMouseClicked(e -> viewModel.stepOut());
        resetButton.setOnMouseClicked(e -> viewModel.reset());
        setupPlayPauseButton();
        initAlgorithms();
    }
}
