package com.demo.parkingmanagementsystem.component3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_PATH = System.getProperty("user.dir") + File.separator + "parking_logs.txt";

    public static void saveSession(ParkingSession session) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(session.toFileFormat());
            writer.newLine();
            writer.flush();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static List<String> readLogs() {
        List<String> logs = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return logs;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) logs.add(line);
            }
        } catch (IOException e) { e.printStackTrace(); }
        return logs;
    }

    public static String findAndRemoveSession(String pin) {
        List<String> logs = readLogs();
        String foundData = null;
        List<String> remaining = new ArrayList<>();
        for (String line : logs) {
            String[] parts = line.split(",");
            if (parts.length > 1 && parts[1].trim().equals(pin.trim())) {
                foundData = line;
            } else {
                remaining.add(line);
            }
        }
        if (foundData != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
                for (String l : remaining) { writer.write(l); writer.newLine(); }
                writer.flush();
            } catch (IOException e) { e.printStackTrace(); }
        }
        return foundData;
    }
}
