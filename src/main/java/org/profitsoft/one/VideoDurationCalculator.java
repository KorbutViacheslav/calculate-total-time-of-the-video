package org.profitsoft.one;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VideoDurationCalculator {
    public static void main(String[] args) {
        String videoFilePath = "E:\\JavaScript\\firsttest.mp4";
        String ffmpegPath = "C:\\ffmpeg\\ffmpeg.exe"; // Замініть на шлях до вашого FFmpeg

        try {
            // Запускаємо FFmpeg як зовнішню команду
            ProcessBuilder processBuilder = new ProcessBuilder(ffmpegPath, "-i", videoFilePath);
            Process process = processBuilder.start();

            // Читаємо вивід FFmpeg
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            double duration = -1;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Duration:")) {
                    String[] parts = line.split(",")[0].split(" ");
                    String durationStr = parts[parts.length - 1];
                    duration = parseDuration(durationStr);
                    break;
                }
            }

            // Виводимо тривалість відео
            if (duration != -1) {
                System.out.println("Duration: " + duration + " seconds");
            } else {
                System.out.println("Failed to read duration from the video file.");
            }

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static double parseDuration(String durationStr) {
        String[] parts = durationStr.split(":");
        double duration = 0;
        double multiplier = 1;
        for (int i = parts.length - 1; i >= 0; i--) {
            duration += Double.parseDouble(parts[i]) * multiplier;
            multiplier *= 60;
        }
        return duration;
    }
}