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

    // Abrir cadastro de médico
    @FXML
    private void abrirCadastroMedico() throws IOException {

        Stage stage = (Stage) nomeField.getScene().getWindow();

        Navegacao.abrirTela(
                stage,
                "tela4.fxml",
                "Cadastro de Médicos"
        );
    }

    // Cadastrar paciente
    @FXML
    private void cadastrarPaciente() {

        String nome = nomeField.getText().trim();
        String id = idField.getText().trim();

        // Validar os campos
        if (nome.isEmpty() || id.isEmpty()) {
            mostrarAlerta(
                    "Campos obrigatórios",
                    "Preencha o nome e o ID do paciente."
            );
            return;
        }

        // Criar paciente
        Paciente paciente = new Paciente(id, nome);

        try {

            // Cadastrar paciente
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

            // Abrir confirmação em tela cheia
            Stage stage = (Stage) nomeField.getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("Paciente cadastrado");
            stage.setFullScreen(true);
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

    // Mostrar alertas
    private void mostrarAlerta(String titulo, String mensagem) {

        Alert alerta = new Alert(Alert.AlertType.WARNING);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);

        alerta.showAndWait();
    }
}
