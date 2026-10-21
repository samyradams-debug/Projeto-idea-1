package com.senai.javafx;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroDeConsulta {

    private LocalDateTime dataHora;

    public RegistroDeConsulta(LocalDateTime dataHora) {
        if (dataHora == null) {
            throw new IllegalArgumentException(
                    "A data e o horário são obrigatórios."
            );
        }

        this.dataHora = dataHora;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        if (dataHora == null) {
            throw new IllegalArgumentException(
                    "A data e o horário são obrigatórios."
            );
        }

        this.dataHora = dataHora;
    }

    public String formatarDataHora() {
        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return dataHora.format(formato);
    }
}