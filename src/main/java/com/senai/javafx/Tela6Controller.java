
package com.senai.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class Tela6Controller {

    @FXML
    private ListView<Consulta> consultasList;

    @FXML
    private ComboBox<Paciente> pacienteCombo;

    @FXML
    private ComboBox<Medico> medicoCombo;

    // Inicializar a tela
    @FXML
    public void initialize() {

        pacienteCombo.getItems().setAll(
                PacienteRepository.listarTodos()
        );

        medicoCombo.getItems().setAll(
                MedicoRepository.listarTodos()
        );

        listarTodas();
    }

    // Listar todas as consultas
    @FXML
    private void listarTodas() {

        consultasList.getItems().setAll(
                ConsultaRepository.listarTodas()
        );
    }

    // Consultar histórico do paciente
    @FXML
    private void consultarHistoricoPaciente() {

        Paciente paciente = pacienteCombo.getValue();

        if (paciente == null) {
            mostrarAlerta(
                    "Selecione um paciente para consultar o histórico."
            );
            return;
        }

        consultasList.getItems().setAll(
                ConsultaRepository.buscarPorPaciente(
                        paciente.getId()
                )
        );
    }

    // Consultar consultas por médico
    @FXML
    private void consultarPorMedico() {

        Medico medico = medicoCombo.getValue();

        if (medico == null) {
            mostrarAlerta(
                    "Selecione um médico para consultar as consultas."
            );
            return;
        }

        consultasList.getItems().setAll(
                ConsultaRepository.buscarPorMedico(
                        medico.getId()
                )
        );
    }

    // Cancelar consulta
    @FXML
    private void cancelarConsulta() {

        Consulta consulta =
                consultasList.getSelectionModel().getSelectedItem();

        if (consulta == null) {
            mostrarAlerta(
                    "Selecione uma consulta para cancelar."
            );
            return;
        }

        try {

            ConsultaRepository.cancelar(consulta.getId());

            listarTodas();

            mostrarAlerta(
                    "Consulta " + consulta.getId()
                            + " cancelada com sucesso."
            );

        } catch (IllegalArgumentException |
                 IllegalStateException e) {

            mostrarAlerta(e.getMessage());
        }
    }

    // Voltar ao menu
    @FXML
    private void voltar() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("menu.fxml")
        );

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) consultasList.getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Sistema de Clínica Médica");
        stage.show();

        // Manter tela cheia
        stage.setFullScreen(true);
    }

    // Mostrar mensagens
    private void mostrarAlerta(String mensagem) {

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle("Gerenciamento de consultas");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);

        alerta.showAndWait();
    }
}