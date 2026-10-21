package com.senai.javafx;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class MedicoRepository {

    private static final Map<String, Medico> medicos =
            new LinkedHashMap<>();

    public static void cadastrar(Medico medico) {

        if (medico == null) {
            throw new IllegalArgumentException(
                    "Informe um médico válido."
            );
        }

        if (medicos.containsKey(medico.getId())) {
            throw new IllegalArgumentException(
                    "Já existe um médico com esse ID."
            );
        }

        medicos.put(medico.getId(), medico);
    }

    public static Medico buscarPorId(String id) {
        return medicos.get(id);
    }

    public static Collection<Medico> listarTodos() {
        return medicos.values();
    }
}
