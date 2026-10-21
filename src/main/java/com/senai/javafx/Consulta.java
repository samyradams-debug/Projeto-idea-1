package com.senai.javafx;

import java.time.LocalDateTime;

public class Consulta {

    private final String id;
    private final Medico medico;
    private final Paciente paciente;
    private final LocalDateTime dataHora;
    private String status;

    public Consulta(String id, Medico medico,
                    Paciente paciente, LocalDateTime dataHora) {

        if (medico == null || paciente == null || dataHora == null) {
            throw new IllegalArgumentException(
                    "Informe médico, paciente e data."
            );
        }
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Informe o ID da consulta."
            );
        }

        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.dataHora = dataHora;
        this.status = "AGENDADA";
    }

    public String getId() {
        return id;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void cancelar() {
        status = "CANCELADA";
    }
}