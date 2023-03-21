package org.cowary.arttrackerback.security;


import java.security.SecureRandom;
import java.util.Base64;

public class TokenGenerator {

//    public static String generateToken(String userId) {
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//
////        // Установить срок действия токена в 1 час
////        long expMillis = nowMillis + 3600000;
////        Date exp = new Date(expMillis);
////
////        // Создать токен
////        Algorithm algorithm = Algorithm.HMAC256(generateSecretKey());
////
////        // Подписать токен и вернуть его в виде строки
////        return JWT.create()
////                .withIssuer("art_tracker_back")
////                .withSubject(userId)
////                .withIssuedAt(now)
////                .sign(algorithm);
//    }

    public static String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[32];
        random.nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }
}
