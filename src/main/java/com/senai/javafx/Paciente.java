package com.senai.javafx;

public class Paciente {


        private final String id;
        private final String nome;

        public Paciente(String id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public String getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }
    }
