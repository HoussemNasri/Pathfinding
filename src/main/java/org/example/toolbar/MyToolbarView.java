package org.example.toolbar;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import org.example.algorithms.PathfindingAlgorithmModel;

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
    private RadioButton destinationMarkerButton;

    @FXML
    private ImageView destinationMarkerIcon;

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
        destinationMarkerButton.selectedProperty()
                               .addListener(((observable, oldValue, newValue) -> destinationMarkerIcon.setImage(ToolbarIconProvider.getGoalMarkerToolIcon(newValue))));
        startMarkerButton.selectedProperty()
                         .addListener(((observable, oldValue, newValue) -> startMarkerIcon.setImage(ToolbarIconProvider.getStartMarkerToolIcon(newValue))));
    }

    private void setupPlayPauseButton() {
        playPauseButton.setOnMouseClicked(event -> viewModel.playPause());

        viewModel.isPlayingProperty().addListener((observable, oldValue, newValue) -> {
            ImageView buttonIcon = (ImageView) playPauseButton.getGraphic();
            buttonIcon.setImage(ToolbarIconProvider.getPlayPauseButtonIcon(newValue));
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

        SelectionModel<String> selectionModel = algorithmsComboBox.getSelectionModel();
        selectionModel.selectFirst();
        selectionModel.selectedIndexProperty().addListener((observable, oldValue, algorithmIndex) -> {
            viewModel.selectedAlgorithmProperty().set(PathfindingAlgorithm.values()[algorithmIndex.intValue()]);
        });
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
