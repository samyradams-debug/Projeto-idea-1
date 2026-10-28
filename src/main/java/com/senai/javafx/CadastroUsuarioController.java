package com.senai.javafx;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;


public class CadastroUsuarioController {


    @FXML
    private TextField nomeField;


    @FXML
    private TextField usuarioField;


    @FXML
    private PasswordField senhaField;


    @FXML
    private PasswordField confirmarSenhaField;


    @FXML
    private void cadastrar() {
        String nome = nomeField.getText().trim();
        String usuario = usuarioField.getText().trim();
        String senha = senhaField.getText();
        String confirmarSenha = confirmarSenhaField.getText();


        if (nome.isEmpty() || usuario.isEmpty()
                || senha.isEmpty() || confirmarSenha.isEmpty()) {
            mostrarAlerta("Atenção",
                    "Preencha todos os campos.");
            return;
        }


        if (senha.length() < 8) {
            mostrarAlerta("Senha inválida",
                    "A senha deve ter pelo menos 8 caracteres.");
            return;
        }


        if (!senha.equals(confirmarSenha)) {
            mostrarAlerta("Senhas diferentes",
                    "A confirmação da senha não corresponde.");
            return;
        }


        try {
            UsuarioRepository.cadastrar(nome, usuario, senha);


            mostrarAlerta("Sucesso",
                    "Usuário cadastrado! Agora você pode entrar.");


            voltar();


        } catch (IllegalArgumentException e) {
            mostrarAlerta("Erro no cadastro", e.getMessage());
        }
    }


    @FXML
    private void voltar() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("login.fxml"));


            Scene scene = new Scene(loader.load());


            Stage stage = (Stage) nomeField
                    .getScene().getWindow();


            stage.setScene(scene);
            stage.setTitle("Login - Hospital Samyra & Heloísa");
            stage.centerOnScreen();
            stage.show();


        } catch (IOException e) {
            mostrarAlerta("Erro",
                    "Não foi possível voltar ao login.");
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
