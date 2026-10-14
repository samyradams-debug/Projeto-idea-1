package com.senai.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Tela3Controller {
    @FXML
    private Label nomeLabel;

    @FXML
    private Label idLabel;

    public void setDadosPaciente(String nome, String id) {

        nomeLabel.setText("Nome: " + nome);
        idLabel.setText("ID: " + id);
    }
}
