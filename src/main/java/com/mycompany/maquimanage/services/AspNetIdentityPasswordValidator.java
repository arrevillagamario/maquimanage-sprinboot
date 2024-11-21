package com.mycompany.maquimanage.services;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class AspNetIdentityPasswordValidator {

    public static boolean validatePassword(String password, String hashedPassword) {
        try {
            // Extraer los datos del hash (versión, salt, hash)
            String[] parts = hashedPassword.split("\\$");
            if (parts.length != 4 || !parts[1].equals("A")) {
                throw new IllegalArgumentException("Formato de hash inválido");
            }

            byte[] salt = Base64.getDecoder().decode(parts[2]);
            byte[] hash = Base64.getDecoder().decode(parts[3]);

            // Crear hash del password ingresado usando PBKDF2 con los mismos parámetros
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 100000, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] testHash = factory.generateSecret(spec).getEncoded();

            // Comparar el hash calculado con el hash almacenado
            return slowEquals(hash, testHash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }
}
