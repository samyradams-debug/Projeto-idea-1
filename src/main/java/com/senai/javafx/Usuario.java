package com.senai.javafx;


public class Usuario {


    private final String nome;
    private final String usuario;
    private final String senhaHash;
    private final String salt;


    public Usuario(String nome, String usuario,
                   String senhaHash, String salt) {
        this.nome = nome;
        this.usuario = usuario;
        this.senhaHash = senhaHash;
        this.salt = salt;
    }


    public String getNome() {
        return nome;
    }


    public String getUsuario() {
        return usuario;
    }


    public String getSenhaHash() {
        return senhaHash;
    }


    public String getSalt() {
        return salt;
    }
}