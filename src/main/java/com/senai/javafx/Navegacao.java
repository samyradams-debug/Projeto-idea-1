
package com.senai.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navegacao {

    public static void abrirTela(
            Stage stage,
            String arquivo,
            String titulo) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource(arquivo)
            );

            Parent root = loader.load();

            Scene scene = new Scene(root);

            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setFullScreen(true);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

