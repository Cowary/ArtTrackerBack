package org.cowary.arttrackerback.security;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretGenerator {

    public static void main(String[] args) {
        // Генерируем случайные байты
        SecureRandom secureRandom = new SecureRandom();
        byte[] secretBytes = new byte[32];
        secureRandom.nextBytes(secretBytes);

        // Создаем ключ из сгенерированных байтов
        Key secretKey = new SecretKeySpec(secretBytes, "HmacSHA512");

        // Кодируем ключ в формат Base64
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        // Выводим ключ в консоль
        System.out.println("Секретный ключ: " + encodedKey);
    }
}
