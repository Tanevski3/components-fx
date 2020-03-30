package com.mtanevski.componentsfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static com.mtanevski.componentsfx.utils.ResourceManagerUtil.get;

public class MainEntry extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // identify main component
        // construct it
        FXMLLoader loader = new FXMLLoader(get("component-chooser.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Choose component to run");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}