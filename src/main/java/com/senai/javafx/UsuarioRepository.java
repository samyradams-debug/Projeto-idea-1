package com.senai.javafx;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;


public class UsuarioRepository {


    private static final Map<String, Usuario> usuarios =
            new LinkedHashMap<>();


    private static final SecureRandom random =
            new SecureRandom();


    public static void cadastrar(String nome, String usuario,
                                 String senha) {


        if (nome == null || nome.isBlank()
                || usuario == null || usuario.isBlank()
                || senha == null || senha.isBlank()) {
            throw new IllegalArgumentException(
                    "Preencha todos os campos.");
        }


        String chave = usuario.trim().toLowerCase();


        if (usuarios.containsKey(chave)) {
            throw new IllegalArgumentException(
                    "Este nome de usuário já está cadastrado.");
        }


        String salt = gerarSalt();
        String hash = gerarHash(senha, salt);


        Usuario novo = new Usuario(
                nome.trim(), usuario.trim(), hash, salt);


        usuarios.put(chave, novo);
    }


    public static Usuario autenticar(String usuario,
                                     String senha) {


        if (usuario == null || senha == null) {
            return null;
        }


        Usuario cadastrado = usuarios.get(
                usuario.trim().toLowerCase());


        if (cadastrado == null) {
            return null;
        }


        String hashDigitado = gerarHash(
                senha, cadastrado.getSalt());


        boolean senhaCorreta = MessageDigest.isEqual(
                hashDigitado.getBytes(StandardCharsets.UTF_8),
                cadastrado.getSenhaHash()
                        .getBytes(StandardCharsets.UTF_8));


        return senhaCorreta ? cadastrado : null;
    }


    private static String gerarSalt() {
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);


        return Base64.getEncoder().encodeToString(bytes);
    }


    private static String gerarHash(String senha, String salt) {
        PBEKeySpec spec = new PBEKeySpec(
                senha.toCharArray(),
                Base64.getDecoder().decode(salt),
                120000,
                256
        );


        try {
            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");


            byte[] hash = factory.generateSecret(spec).getEncoded();


            return Base64.getEncoder().encodeToString(hash);


        } catch (Exception e) {
            throw new IllegalStateException(
                    "Não foi possível verificar a senha.", e);
        } finally {
            spec.clearPassword();
        }
    }
}