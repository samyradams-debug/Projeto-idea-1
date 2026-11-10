
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
                HelloApplication.class.getResource("login.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Login - Hospital Samyra & Heloísa");
        stage.setScene(scene);

        // Permitir que a janela ocupe a tela inteira
        stage.setResizable(true);

        // Mostrar a janela
        stage.show();

        // Ativar tela cheia depois de mostrar
        stage.setFullScreen(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
