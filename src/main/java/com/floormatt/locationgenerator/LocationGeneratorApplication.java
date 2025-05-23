package com.floormatt.locationgenerator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LocationGeneratorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LocationGeneratorApplication.class.getResource("location-generator.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        //set the app icon
        Image icon = new Image(Objects.requireNonNull(LocationGeneratorApplication.class.getResourceAsStream("WhsLocationGeneratorIcon.png")));
        stage.getIcons().add(icon);

        stage.setTitle("Location Generator");
        stage.setScene(scene);

        //prevent screen size change
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}