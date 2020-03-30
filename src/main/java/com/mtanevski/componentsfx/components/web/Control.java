package com.mtanevski.componentsfx.components.web;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static com.mtanevski.componentsfx.utils.ResourceManagerUtil.get;

public class Control extends AnchorPane {

    @FXML
    private WebView web;

    public void initialize() throws URISyntaxException, MalformedURLException {
        WebEngine engine = web.getEngine();
        engine.load(get("components/web/index.html").toURI().toURL().toString());
        engine.getLoadWorker().stateProperty().addListener(
                (ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) -> {
                    if (newState == Worker.State.SUCCEEDED) {
                        JSObject win = (JSObject) engine.executeScript("window");
                        win.setMember("app", new Control.JavaApp());
                    }
                });
    }

    public class JavaApp {
        public void exit() {
            Platform.exit();
        }
    }
}
