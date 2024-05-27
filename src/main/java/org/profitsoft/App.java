package org.profitsoft;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Author: Viacheslav Korbut
 * Date: 15.05.2024
 */

public class App {
    public static void main(String[] args) {
        String folderPath = "E:\\JavaScript\\LESSONS\\[Udemy, Bogdan Stashchuk] React - Полный Курс по React и Redux\\";
        String ffmpegPath = "C:\\ffmpeg\\ffmpeg.exe";
        AtomicDouble totalDuration = new AtomicDouble(0);

        processFiles(new File(folderPath), ffmpegPath, totalDuration);
        System.out.println("Загальна тривалість: " + formatDuration(totalDuration.get()));
    }

    private static void processFiles(File folder, String ffmpegPath, AtomicDouble totalDuration) {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                processFiles(file, ffmpegPath, totalDuration);
            } else if (file.isFile() && file.getName().endsWith(".mp4")) {
                double duration = getDuration(file, ffmpegPath);
                if (duration != -1) {
                    totalDuration.addAndGet(duration);
                    System.out.println("Тривалість " + file.getName() + ": " + formatDuration(duration));
                } else {
                    System.out.println("Не вдалося прочитати тривалість з " + file.getName());
                }
            }
        }
    }

    private static double getDuration(File file, String ffmpegPath) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(ffmpegPath, "-i", file.getAbsolutePath());
            Process process = processBuilder.start();

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

            process.waitFor();
            return duration;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1;
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

    private static String formatDuration(double duration) {
        long hours = (long) Math.floor(duration / 3600);
        long minutes = (long) Math.floor((duration % 3600) / 60);
        long seconds = (long) Math.floor(duration % 60);
        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    }
}