package com.mtanevski.componentsfx;

import com.mtanevski.componentsfx.utils.ResourceManagerUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ComponentChooser {

    private static final String COMPONENTS_PATH = "components";
    private static final String STARTING_FXML = "/index.fxml";

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button runButton;

    // called by the FXML loader after the components declared above are injected:
    public void initialize() throws IOException {
        List<String> components = ResourceManagerUtil.getClasspathEntriesByPath(COMPONENTS_PATH);
        choiceBox.setItems(FXCollections.observableList(components));
        choiceBox.getSelectionModel().selectFirst();
        if(components.isEmpty()) runButton.setDisable(true);
    }

    public void startComponent(ActionEvent actionEvent) {
        String selectedItem = choiceBox.getSelectionModel().getSelectedItem();

        try {
            Parent parent = FXMLLoader.load(ResourceManagerUtil.get(COMPONENTS_PATH + "/" + selectedItem + STARTING_FXML));
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            stage.hide(); //optional
            stage.setTitle(selectedItem);
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }
}
