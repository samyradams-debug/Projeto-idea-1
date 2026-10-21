package com.senai.javafx;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

        pacienteCombo.setItems(
                FXCollections.observableArrayList(
                        PacienteRepository.listarTodos()
                )
        );

        medicoCombo.setItems(
                FXCollections.observableArrayList(
                        MedicoRepository.listarTodos()
                )
        );

        pacienteCombo.setCellFactory(lista ->
                new javafx.scene.control.ListCell<Paciente>() {
                    @Override
                    protected void updateItem(
                            Paciente paciente, boolean vazio) {
                        super.updateItem(paciente, vazio);

                        setText(vazio || paciente == null
                                ? null : paciente.getNome());
                    }
                });

        pacienteCombo.setButtonCell(
                new javafx.scene.control.ListCell<Paciente>() {
                    @Override
                    protected void updateItem(
                            Paciente paciente, boolean vazio) {
                        super.updateItem(paciente, vazio);

                        setText(vazio || paciente == null
                                ? null : paciente.getNome());
                    }
                });

        medicoCombo.setCellFactory(lista ->
                new javafx.scene.control.ListCell<Medico>() {
                    @Override
                    protected void updateItem(
                            Medico medico, boolean vazio) {
                        super.updateItem(medico, vazio);

                        setText(vazio || medico == null
                                ? null : medico.getNome()
                                + " - " + medico.getEspecialidade());
                    }
                });

        medicoCombo.setButtonCell(
                new javafx.scene.control.ListCell<Medico>() {
                    @Override
                    protected void updateItem(
                            Medico medico, boolean vazio) {
                        super.updateItem(medico, vazio);

                        setText(vazio || medico == null
                                ? null : medico.getNome()
                                + " - " + medico.getEspecialidade());
                    }
                });
    }

    @FXML
    private void registrarConsulta() {

        Paciente paciente = pacienteCombo.getValue();
        Medico medico = medicoCombo.getValue();

        if (paciente == null || medico == null
                || dataPicker.getValue() == null
                || horaField.getText().trim().isEmpty()) {

            mostrarAlerta(
                    "Campos obrigatórios",
                    "Selecione o paciente, o médico, a data e o horário."
            );
            return;
        }

        try {
            LocalTime hora = LocalTime.parse(
                    horaField.getText().trim(),
                    DateTimeFormatter.ofPattern("HH:mm")
            );

            LocalDateTime dataHora = LocalDateTime.of(
                    dataPicker.getValue(), hora
            );

            Consulta consulta = ConsultaRepository.cadastrar(
                    medico, paciente, dataHora
            );

            mostrarAlerta(
                    "Consulta registrada",
                    "ID: " + consulta.getId()
                            + "\nPaciente: " + paciente.getNome()
                            + "\nMédico: " + medico.getNome()
                            + "\nData: " + dataHora.format(
                            DateTimeFormatter.ofPattern(
                                    "dd/MM/yyyy HH:mm"))
            );

            pacienteCombo.setValue(null);
            medicoCombo.setValue(null);
            dataPicker.setValue(null);
            horaField.clear();

        } catch (DateTimeParseException e) {

            mostrarAlerta(
                    "Horário inválido",
                    "Digite o horário no formato HH:mm, por exemplo 14:30."
            );

        } catch (IllegalArgumentException e) {

            mostrarAlerta("Erro", e.getMessage());
        }
    }

    @FXML
    private void voltar() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("hello-view.fxml")
        );

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) horaField.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Cadastro de Pacientes");
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
