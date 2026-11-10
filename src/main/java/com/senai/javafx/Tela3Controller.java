
package com.senai.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Tela3Controller {

    @FXML
    private Label nomeLabel;

    @FXML
    private Label idLabel;

    // Mostrar os dados do paciente
    public void setDadosPaciente(String nome, String id) {

        nomeLabel.setText("Nome: " + nome);
        idLabel.setText("ID: " + id);
    }

    // Voltar ao cadastro de pacientes
    @FXML
    private void voltarAoCadastro() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("hello-view.fxml")
        );

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) nomeLabel.getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Cadastro de Pacientes");
        stage.show();

        // Manter tela cheia
        stage.setFullScreen(true);
    }
}
