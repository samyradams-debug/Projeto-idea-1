
package com.senai.javafx;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class PacienteRepository {

    private static final Map<String, Paciente> pacientes =
            new LinkedHashMap<>();

    public static void cadastrar(Paciente paciente) {

        if (paciente == null) {
            throw new IllegalArgumentException(
                    "Informe um paciente válido.");
        }

        if (pacientes.containsKey(paciente.getId())) {
            throw new IllegalArgumentException(
                    "Já existe um paciente cadastrado com esse ID.");
        }

        pacientes.put(paciente.getId(), paciente);
    }

    public static Paciente buscarPorId(String id) {
        return pacientes.get(id);
    }

    public static Collection<Paciente> listarTodos() {
        return pacientes.values();
    }
}
