package model;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebMap extends Application {
    @Override
    public void start(Stage stage) {
        //create web engine and view
        final WebEngine webEngine = new WebEngine(getClass().getResource("googlemap.html").toString());
        final WebView webView = new WebView(webEngine);
        //create map type buttons
        final ToggleGroup mapTypeGroup = new ToggleGroup();
        final ToggleButton road = new ToggleButton("Road");
        road.setSelected(true);
        road.setToggleGroup(mapTypeGroup);
        final ToggleButton satellite = new ToggleButton("Satellite");
        satellite.setToggleGroup(mapTypeGroup);
        final ToggleButton hybrid = new ToggleButton("Hybrid");
        hybrid.setToggleGroup(mapTypeGroup);
        final ToggleButton terrain = new ToggleButton("Terrain");
        terrain.setToggleGroup(mapTypeGroup)
        mapTypeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (road.isSelected()) {
                    webEngine.executeScript("document.setMapTypeRoad()");
                } else if (satellite.isSelected()) {
                    webEngine.executeScript("document.setMapTypeSatellite()");
                } else if (hybrid.isSelected()) {
                    webEngine.executeScript("document.setMapTypeHybrid()");
                } else if (terrain.isSelected()) {
                    webEngine.executeScript("document.setMapTypeTerrain()");
                }
            }
        });
        //add map source toggles
        ToggleGroup mapSourceGroup = new ToggleGroup();
        final ToggleButton google = new ToggleButton("Google");
        google.setSelected(true);
        google.setToggleGroup(mapSourceGroup);
        final ToggleButton yahoo = new ToggleButton("Yahoo");
        yahoo.setToggleGroup(mapSourceGroup);
        final ToggleButton bing = new ToggleButton("Bing");
        bing.setToggleGroup(mapSourceGroup);
        //listen to selected source
        mapSourceGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                terrain.setDisable(false);
                if (google.isSelected()) {
                    webEngine.load(getClass().getResource("googlemap.html").toString());
                } else if (yahoo.isSelected()) {
                    webEngine.load(getClass().getResource("yahoomap.html").toString());
                } else if (bing.isSelected()) {
                    webEngine.load(getClass().getResource("bingmap.html").toString());
                }
                mapTypeGroup.selectToggle(road);
            }
        });
        //add search
        final TextField search = new TextField("95054");
        search.setPrefColumnCount(12);
        search.setPromptText("Search");

        //add Tool


        //create scene
        Stage.setTitle("Web map");
        Scene scene = new Scene(webView, 1000, 700, Color.web("#666970"));
        stage.setScene(scene);
        //show stage
        stage.setVisible(true);

        static {
            System.setProperty("java.net.useSystemProxies", "true");
        }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
}
