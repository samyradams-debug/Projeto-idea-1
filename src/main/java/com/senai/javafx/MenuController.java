

package com.senai.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private void abrirPacientes() throws IOException {
        abrirTela("hello-view.fxml", "Cadastro de Pacientes");
    }

    @FXML
    private void abrirMedicos() throws IOException {
        abrirTela("tela4.fxml", "Cadastro de Médicos");
    }

    @FXML
    private void abrirConsultas() throws IOException {
        abrirTela("tela5.fxml", "Registro de Consultas");
    }

    @FXML
    private void abrirGerenciamento() throws IOException {
        abrirTela("tela6.fxml", "Gerenciamento de Consultas");
    }

    private void abrirTela(String arquivo, String titulo)
            throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(arquivo));

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) javafx.stage.Window
                .getWindows().stream()
                .filter(javafx.stage.Window::isShowing)
                .findFirst()
                .orElseThrow();

        stage.setScene(scene);
        stage.setTitle(titulo);
        stage.show();
    }
}
