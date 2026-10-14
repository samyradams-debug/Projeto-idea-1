package com.senai.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField idField;

    @FXML
    private void cadastrarPaciente() {

        String nome = nomeField.getText().trim();
        String id = idField.getText().trim();

        // 1. Validar os campos
        if (nome.isEmpty() || id.isEmpty()) {
            mostrarAlerta(
                    "Campos obrigatórios",
                    "Preencha o nome e o ID do paciente."
            );
            return;
        }

        // 2. Criar o paciente
        Paciente paciente = new Paciente(id, nome);

        // 3. Cadastrar e abrir a tela de confirmação
        try {
            PacienteRepository.cadastrar(paciente);

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("tela3.fxml")
            );

            Scene scene = new Scene(loader.load());

            Tela3Controller controller = loader.getController();
            controller.setDadosPaciente(
                    paciente.getNome(),
                    paciente.getId()
            );

            Stage stage = (Stage) nomeField.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Paciente cadastrado");
            stage.show();

        } catch (IllegalArgumentException e) {
            mostrarAlerta("ID já cadastrado", e.getMessage());

        } catch (IOException e) {
            mostrarAlerta(
                    "Erro ao abrir a tela",
                    "Não foi possível abrir a tela de confirmação."
            );

            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}