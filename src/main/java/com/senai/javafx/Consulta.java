
package com.senai.javafx;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consulta {

    private final String id;
    private final Medico medico;
    private final Paciente paciente;
    private final LocalDateTime dataHora;
    private String status;

    public Consulta(String id, Medico medico,
                    Paciente paciente, LocalDateTime dataHora) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(
                    "Informe o ID da consulta.");
        }

        if (medico == null) {
            throw new IllegalArgumentException(
                    "Selecione um médico cadastrado.");
        }

        if (paciente == null) {
            throw new IllegalArgumentException(
                    "Selecione um paciente cadastrado.");
        }

        if (dataHora == null) {
            throw new IllegalArgumentException(
                    "Informe a data e o horário.");
        }

        this.id = id.trim();
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
        if ("CANCELADA".equals(status)) {
            throw new IllegalStateException(
                    "Esta consulta já foi cancelada.");
        }

        status = "CANCELADA";
    }

    public String formatarDataHora() {
        return dataHora.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }@Override
    public String toString() {
        return "Código: " + id
                + " | " + formatarDataHora()
                + " | Paciente: " + paciente.getNome()
                + " | Médico: " + medico.getNome()
                + " | Status: " + status;
    }
}
