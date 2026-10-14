package com.senai.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloController {
    @FXML
    private TextField nomeField;

    @FXML
    private TextField idField;

    @FXML
    private void cadastrarPaciente() throws IOException {

        String nome = nomeField.getText();
        String id = idField.getText();

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("tela3.fxml")
        );

        Scene scene = new Scene(loader.load());

        Tela3Controller controller = loader.getController();

        controller.setDadosPaciente(nome, id);

        Stage stage = (Stage) nomeField.getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }
}
