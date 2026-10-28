
package com.senai.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Tela4Controller {

    @FXML
    private TextField idField;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField especialidadeField;

    @FXML
    private void cadastrarMedico() {
        String id = idField.getText().trim();
        String nome = nomeField.getText().trim();
        String especialidade = especialidadeField.getText().trim();

        if (id.isEmpty() || nome.isEmpty()
                || especialidade.isEmpty()) {
            mostrarAlerta(
                    "Campos obrigatórios",
                    "Preencha o ID, o nome e a especialidade.");
            return;
        }

        try {
            Medico medico = new Medico(id, nome, especialidade);
            MedicoRepository.cadastrar(medico);

            mostrarAlerta(
                    "Sucesso",
                    "Médico cadastrado com sucesso!");

            idField.clear();
            nomeField.clear();
            especialidadeField.clear();

        } catch (IllegalArgumentException e) {
            mostrarAlerta("Erro no cadastro", e.getMessage());
        }
    }

    @FXML
    private void voltar() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("menu.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) idField.getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Sistema de Clínica Médica");
        stage.show();
    }

    private void mostrarAlerta(
            String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
