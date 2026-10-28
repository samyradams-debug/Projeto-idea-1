
package com.senai.javafx;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsultaRepository {

    private static final Map<String, Consulta> consultas =
            new LinkedHashMap<>();

    private ConsultaRepository() {
    }

    public static void cadastrar(Consulta consulta) {

        if (consulta == null) {
            throw new IllegalArgumentException(
                    "Informe uma consulta válida.");
        }

        if (MedicoRepository.buscarPorId(
                consulta.getMedico().getId()) == null) {
            throw new IllegalArgumentException(
                    "O médico não está cadastrado.");
        }

        if (PacienteRepository.buscarPorId(
                consulta.getPaciente().getId()) == null) {
            throw new IllegalArgumentException(
                    "O paciente não está cadastrado.");
        }

        if (consultas.containsKey(consulta.getId())) {
            throw new IllegalArgumentException(
                    "Já existe uma consulta com esse ID.");
        }

        consultas.put(consulta.getId(), consulta);
    }

    public static Consulta buscarPorId(String id) {
        return consultas.get(id);
    }

    public static Collection<Consulta> listarTodas() {
        return consultas.values();
    }

    public static Collection<Consulta> buscarPorMedico(
            String idMedico) {

        return consultas.values().stream()
                .filter(c -> c.getMedico().getId()
                        .equals(idMedico))
                .collect(Collectors.toList());
    }

    public static Collection<Consulta> buscarPorPaciente(
            String idPaciente) {

        return consultas.values().stream()
                .filter(c -> c.getPaciente().getId()
                        .equals(idPaciente))
                .collect(Collectors.toList());
    }

    public static void cancelar(String id) {

        Consulta consulta = buscarPorId(id);

        if (consulta == null) {
            throw new IllegalArgumentException(
                    "Consulta não encontrada.");
        }

        consulta.cancelar();
    }
}


