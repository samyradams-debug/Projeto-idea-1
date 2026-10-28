package com.senai.javafx;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;


public class LoginController {


    @FXML
    private TextField usuarioField;


    @FXML
    private PasswordField senhaField;


    @FXML
    private void entrar() {
        String usuario = usuarioField.getText().trim();
        String senha = senhaField.getText();


        if (usuario.isEmpty() || senha.isEmpty()) {
            mostrarAlerta("Atenção",
                    "Informe o usuário e a senha.");
            return;
        }


        Usuario usuarioAutenticado =
                UsuarioRepository.autenticar(usuario, senha);


        if (usuarioAutenticado == null) {
            mostrarAlerta("Login inválido",
                    "Usuário ou senha incorretos.");
            senhaField.clear();
            return;
        }


        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("menu.fxml"));


            Scene scene = new Scene(loader.load());


            Stage stage = (Stage) usuarioField
                    .getScene().getWindow();


            stage.setScene(scene);
            stage.setTitle("Hospital Samyra & Heloísa");
            stage.centerOnScreen();
            stage.show();


        } catch (IOException e) {
            mostrarAlerta("Erro",
                    "Não foi possível abrir o menu.");
            e.printStackTrace();
        }
    }


    @FXML
    private void abrirCadastro() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "cadastro-usuario.fxml"));


            Scene scene = new Scene(loader.load());


            Stage stage = (Stage) usuarioField
                    .getScene().getWindow();


            stage.setScene(scene);
            stage.setTitle("Cadastro de Usuário");
            stage.centerOnScreen();
            stage.show();


        } catch (IOException e) {
            mostrarAlerta("Erro",
                    "Não foi possível abrir o cadastro.");
            e.printStackTrace();
        }
    }


    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}