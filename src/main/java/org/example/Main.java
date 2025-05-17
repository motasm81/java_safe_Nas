package org.example;
import javax.sound.sampled.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class Main {

    public static byte[] readAudioBytes(String filePath) throws Exception {
        File audioFile = new File("C:/Users/user/Desktop/guitar-slow-emotion-330121.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int read;
        while ((read = audioInputStream.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }

        return out.toByteArray();
    }

    public static String hashBytes(byte[] data, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] hash = digest.digest(data);
        return HexFormat.of().formatHex(hash);
    }

    public static void main(String[] args) {
        String filePath = "audio/sample.wav";

        try {
            byte[] audioBytes = readAudioBytes(filePath);
            String hash = hashBytes(audioBytes, "SHA-256");

            System.out.println("Audio Hash (SHA-256): " + hash);

            // You can write hash to a file and compare later
            // e.g., Files.writeString(Path.of("hash.txt"), hash);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
