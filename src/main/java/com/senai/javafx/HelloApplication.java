
package com.senai.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                HelloApplication.class.getResource("menu.fxml"));

        Scene scene = new Scene(loader.load(), 500, 420);

        stage.setTitle("Sistema de Clínica Médica");
        stage.setScene(scene);
        stage.setMinWidth(450);
        stage.setMinHeight(350);
        stage.show();
    }
}
