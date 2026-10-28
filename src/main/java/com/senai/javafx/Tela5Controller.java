
package com.senai.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class Tela5Controller {

    @FXML
    private ComboBox<Paciente> pacienteCombo;

    @FXML
    private ComboBox<Medico> medicoCombo;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private TextField horaField;

    @FXML
    public void initialize() {
        pacienteCombo.getItems().setAll(
                PacienteRepository.listarTodos());

        medicoCombo.getItems().setAll(
                MedicoRepository.listarTodos());

        dataPicker.setDayCellFactory(picker ->
                new javafx.scene.control.DateCell() {
                    @Override
                    public void updateItem(
                            LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(empty ||
                                date.isBefore(LocalDate.now()));
                    }
                });
    }

    @FXML
    private void registrarConsulta() {
        Paciente paciente = pacienteCombo.getValue();
        Medico medico = medicoCombo.getValue();

        if (paciente == null || medico == null
                || dataPicker.getValue() == null
                || horaField.getText().isBlank()) {
            mostrarAlerta(
                    "Campos obrigatórios",
                    "Selecione o paciente, o médico, a data e o horário.");
            return;
        }

        try {
            LocalDate data = dataPicker.getValue();

            LocalTime hora = LocalTime.parse(
                    horaField.getText().trim(),
                    DateTimeFormatter.ofPattern("HH:mm"));

            LocalDateTime dataHora =
                    LocalDateTime.of(data, hora);

            if (dataHora.isBefore(LocalDateTime.now())) {
                mostrarAlerta(
                        "Data inválida",
                        "A consulta deve ser agendada para o presente ou futuro.");
                return;
            }

            String id = UUID.randomUUID().toString()
                    .substring(0, 8).toUpperCase();

            Consulta consulta = new Consulta(
                    id, medico, paciente, dataHora);

            ConsultaRepository.cadastrar(consulta);

            mostrarAlerta(
                    "Consulta registrada",
                    "Consulta cadastrada com sucesso!\n\n"
                            + "Código: " + consulta.getId()
                            + "\nPaciente: " + paciente.getNome()
                            + "\nMédico: " + medico.getNome()
                            + "\nData: " + consulta.formatarDataHora()
                            + "\nStatus: " + consulta.getStatus());

            pacienteCombo.getSelectionModel().clearSelection();
            medicoCombo.getSelectionModel().clearSelection();
            dataPicker.setValue(null);
            horaField.clear();

        } catch (DateTimeParseException e) {
            mostrarAlerta(
                    "Horário inválido",
                    "Digite o horário no formato HH:mm, por exemplo 14:30.");
        } catch (IllegalArgumentException e) {
            mostrarAlerta("Erro", e.getMessage());
        }
    }

    @FXML
    private void voltar() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("menu.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) horaField.getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Sistema de Clínica Médica");
        stage.show();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
