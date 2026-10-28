
package com.senai.javafx;

public class Paciente {

    private final String id;
    private final String nome;

    public Paciente(String id, String nome) {
        if (id == null || id.isBlank()
                || nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "ID e nome do paciente são obrigatórios.");
        }

        this.id = id.trim();
        this.nome = nome.trim();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + " (ID: " + id + ")";
    }
}

