package dev.skyherobrine.project.backend.utils;

import java.util.Base64;

public class EncodeDecodeUtil {

    public static String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }

    public static String decode(String message) {
        return new String(Base64.getDecoder().decode(message));
    }
}
