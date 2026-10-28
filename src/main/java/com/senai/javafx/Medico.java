
package com.senai.javafx;

public class Medico {

    private final String id;
    private final String nome;
    private final String especialidade;

    public Medico(String id, String nome, String especialidade) {
        if (id == null || id.isBlank()
                || nome == null || nome.isBlank()
                || especialidade == null
                || especialidade.isBlank()) {
            throw new IllegalArgumentException(
                    "Preencha o ID, nome e especialidade do médico.");
        }

        this.id = id.trim();
        this.nome = nome.trim();
        this.especialidade = especialidade.trim();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    @Override
    public String toString() {
        return nome + " - " + especialidade
                + " (ID: " + id + ")";
    }
}
